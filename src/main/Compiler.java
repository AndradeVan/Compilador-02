package main;

import ast.*;
import lexer.Lexer;
import lexer.Symbol;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Compiler {

  private Lexer lexer;
  private Hashtable<String, Variable> symbolTable;

  public Program compile(char []input){
    lexer = new Lexer(input);
    symbolTable = new Hashtable<>();
    lexer.nextToken();

    Program program = program();

    return program;

  }

  private Program program() {

    List<Stat> statList = new ArrayList<>();
    ArrayList<VarListStat> arrayVariable = null;

    statList.add(stat());

    while(lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token ==  Symbol.IF || lexer.token == Symbol.FOR
    || lexer.token == Symbol.WHILE || lexer.token == Symbol.PRINT || lexer.token == Symbol.PRINTLN) {
      statList.add(stat());

    }
    return new Program(statList);
  }

  private Stat stat() {
    switch (lexer.token) {
      case VAR:
        return varList();
      case IDENT:
        return assignStat();
      case IF:
        return ifStat();
      case FOR:
        return forStat();
      case WHILE:
        return whileStat();
      case PRINT:
        return printStat();
      case PRINTLN:
        return printlnStat();
    }
    return null;
  }

  private PrintStat printStat() {
    lexer.nextToken();
    Expr e = expr();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Esqueceu ;");
    }
    lexer.nextToken();

    return new PrintStat(e);
  }

  private PrintlnStat printlnStat() {
    lexer.nextToken();
    Expr e = expr();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Esqueceu ;");
    }
    lexer.nextToken();

    return new PrintlnStat(e);
  }

  private WhileStat whileStat() {
    lexer.nextToken();
    Expr e = expr();
    StatList statList = statList();

    return new WhileStat(e, statList);
  }

  private ForStat forStat() {
    int first, second;
    lexer.nextToken();
    if(lexer.token == Symbol.IDENT) {
      String ident = lexer.getStringValue();

      //adiciona variavel no escopo do for
      if( symbolTable.get(ident) != null) {
        System.out.println("Variavel " + ident + " já foi declarada.");
      }

      Variable v = new Variable(ident,Type.intType);
      symbolTable.put(ident,v);

      lexer.nextToken();

      if(lexer.token != Symbol.IN) {
        System.out.println("Esperando in");
      }
      lexer.nextToken();
      Expr startExpr = expr();
      first = lexer.getValueNumber();
      if(lexer.token != Symbol.TWODOT) {
        System.out.println("Esperando ..");
      }
      lexer.nextToken();
      Expr endExpr = expr();
      second = lexer.getValueNumber();

      //isso tá errado, pq podemos declarar uma variavel a = 1 e usar a variavel e nao o numero
      //verificar se o primeiro elemento é maior que o segundo
      if(first > second) {
        System.out.println("Erro de execução");
        System.exit(0);
      }
      StatList statList = statList();
      //remover a variavel do escopo do for
      symbolTable.remove(ident, v);
      return new ForStat(ident, startExpr, endExpr, statList );
    }else {
      return null;
    }
  }

  private IfStat ifStat() {
    lexer.nextToken();
    //verificar se a expressao é boolean

    Expr e = expr();

    StatList ifPart = statList();
    StatList elsePart = null;
    if(lexer.token == Symbol.ELSE){
      //System.out.println("Esperando Else");
      lexer.nextToken();
      elsePart = statList();
    }

    return new IfStat(e, ifPart, elsePart);
  }

  private StatList statList() {
    StatList statList = new StatList();

    if(lexer.token != Symbol.OPENCHAVE){
      System.out.println("Esperando {");
    }

    lexer.nextToken();

    //{ stat }
    while (lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token == Symbol.FOR || lexer.token == Symbol.IF ||
            lexer.token == Symbol.WHILE || lexer.token == Symbol.PRINTLN || lexer.token == Symbol.PRINT) {
      statList.add(stat());
    }

    lexer.nextToken();

    return statList;
  }

  private AssignStat assignStat() {

    String name = lexer.getStringValue();

    Variable v = symbolTable.get(name);
    if(v == null) {
      System.out.println("Variavel " + name + " não foi declarada.");
    }
    lexer.nextToken();

    if(lexer.token != Symbol.ASSIGN) {
      System.out.println("Faltando =");
    }
    lexer.nextToken();

    Expr e = expr();

    if(lexer.token != Symbol.SEMICOLON) {
      System.out.println("Faltando ;");
    }

    if(v.getType() != e.getType()){
      System.out.println("A variavel "+ name + " é do tipo "+ v.getType());
    }

    lexer.nextToken();
    return new AssignStat(name, e);
  }

  private Expr expr() {
    Expr left, right;
    left = orExpr();
    while(lexer.token == Symbol.PLUSPLUS) {
      lexer.nextToken();
      right = orExpr();
      left = new CompositeExpr(left, Symbol.PLUSPLUS, right);
    }
    return left;
  }

  private Expr orExpr() {
    Expr left, right;

    left = andExpr();
    if (lexer.token == Symbol.OR) {
      lexer.nextToken();
      right = andExpr();
      if( left.getType() != Type.booleanType || right.getType() != Type.booleanType){
        System.out.println("Opaa.... ! tem que ser com operador boolean");
      }
      left = new CompositeExpr(left, Symbol.OR, right);
    }
    return left;
  }

  private Expr andExpr() {
    Expr left, right;
    left = relExpr();
    if(lexer.token == Symbol.AND){
      lexer.nextToken();
      right = relExpr();
      if( left.getType() != Type.booleanType || right.getType() != Type.booleanType){
        System.out.println("Os valores da operação "+ Symbol.AND + " tem que ser do tipo Boolean." );
      }
      left = new CompositeExpr(left, Symbol.AND, right);
    }
    return left;
  }

  private Expr relExpr() {
    Expr left, right;

    left = addExpr();
    Symbol op = lexer.token;
    if(op == Symbol.LT || op == Symbol.LE || op == Symbol.GE || op == Symbol.GT ||
            op == Symbol.EQ || op == Symbol.NEQ) {
      lexer.nextToken();
      right = addExpr();
      if( left.getType() != right.getType()){
        System.out.println("Os valores da operação "+ op + " tem que ser do mesmo tipo");
      }
      left = new CompositeExpr(left, op, right);
    }

    return left;
  }

  private Expr addExpr() {
    Expr left, right;
    left = multExpr();
    Symbol op;
    while((op = lexer.token) == Symbol.PLUS || op == Symbol.MINUS) {
      lexer.nextToken();
      right = multExpr();
      if( left.getType() != Type.intType || right.getType() != Type.intType){
        System.out.println("Opaa.... ! tem que ser com operador inteiroo");
      }
      left = new CompositeExpr(left, op, right);
    }
    return left;
  }

  private Expr multExpr() {
    Expr left, right;
    left = simpleExpr();
    Symbol op;
    while((op = lexer.token) == Symbol.MULT || op == Symbol.DIV || op == Symbol.REMAINDER) {
      lexer.nextToken();
      right = simpleExpr();
      if( left.getType() != Type.intType || right.getType() != Type.intType){
        System.out.println("Opaa.... ! tem que ser com operador inteiro");
      }
      left = new CompositeExpr(left, op, right);
    }
    return left;
  }

  private Expr simpleExpr() {
    Expr e;
    switch(lexer.token) {
      case CHARACTER:
        String value = lexer.getStringIdent();
        lexer.nextToken();
        return new StringExpr(value);
      case NUMBER:
        return number();
      case TRUE:
        lexer.nextToken();
        return BooleanExpr.True;
      case FALSE:
        lexer.nextToken();
        return BooleanExpr.False;
      case NOT:
        lexer.nextToken();
        e = expr();
        if(e.getType() != Type.booleanType){
          System.out.println("Opaa.... ! tem que ser com operador boolean");
        }
        return new UnaryExpr(e, Symbol.NOT);
      case PLUS:
        lexer.nextToken();
        e = expr();
        if( e.getType() != Type.intType){
          System.out.println("Opaa.... ! tem que ser com operador inteiro");
        }
        return new UnaryExpr(e, Symbol.PLUS);
      case MINUS:
        lexer.nextToken();
        e = expr();
        if( e.getType() != Type.intType){
          System.out.println("Opaa.... ! tem que ser com operador inteiro");
        }
        return new UnaryExpr(e, Symbol.MINUS);
      case PLUSPLUS:
        lexer.nextToken();
        e = expr();
        return new UnaryExpr(e, Symbol.PLUSPLUS);
      case LEFTPAR:
        lexer.nextToken();
        e = expr();
        if(lexer.token != Symbol.RIGHTPAR){
          System.out.println("Erro faltando )");
        }
        lexer.nextToken();
        return e;
      default:
        if(lexer.token != Symbol.IDENT){
          System.out.println("Erro");
        }

        String name = lexer.getStringValue();

        Variable v = symbolTable.get(name);

        if(v == null) {
          System.out.println("Variavel " + name + " não foi declarada.");
        }
        lexer.nextToken();

        return new VariableExpr(v);
    }
  }

  private VarListStat varList() {

    lexer.nextToken();
    Type type = type();

    if(lexer.token != Symbol.IDENT) {
      System.out.println("Erro ao criar uma variavel");
    }

    String name = lexer.getStringValue();

    if(symbolTable.get(name) != null) {
      System.out.println("Variavel "+ name + " já foi declarada.");
    }

    Variable v = new Variable(name, type);
    symbolTable.put(name,v);

    lexer.nextToken();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Faltando ;");
    }
    lexer.nextToken();

    return new VarListStat(type, name);
  }

  private Type type() {
    Type result;
    switch(lexer.token) {
      case Boolean:
        result = Type.booleanType;
        break;
      case Int:
        result = Type.intType;
        break;
      case String:
        result = Type.stringType;
        break;
      default:
        throw new IllegalStateException("Compilador não suporta o tipo: " + lexer.token);
    }
    lexer.nextToken();
    return result;
  }

  private NumberExpr number() {

    int value = lexer.getValueNumber();
    lexer.nextToken();

    return new NumberExpr(value);
  }
}
