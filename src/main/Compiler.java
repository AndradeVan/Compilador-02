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
  private Hashtable<String, Type> typeVar;
  private ArrayList<Symbol> symbolToken;

  public Program compile(char []input){
    lexer = new Lexer(input);
    symbolTable = new Hashtable<>();
    typeVar = new Hashtable<>();
    symbolToken = new ArrayList<Symbol>();
    lexer.nextToken();

    Program program = program();

    return program;

  }

  private Program program() {

    List<Stat> statList = new ArrayList<>();

    statList.add(stat());

    while(lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token ==  Symbol.IF || lexer.token == Symbol.FOR
    || lexer.token == Symbol.WHILE || lexer.token == Symbol.PRINT || lexer.token == Symbol.PRINTLN) {
      statList.add(stat());
    }
    if ( lexer.token != Symbol.EOF ) {
      System.out.println("Esperado EOF");
      System.exit(0);
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

    symbolToken.add(lexer.token);
    ArrayList<Expr> exprList = null;
    lexer.nextToken();
    exprList = exprList();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Esqueceu ;");
      System.exit(0);
    }
    lexer.nextToken();

    return new PrintStat(exprList);
  }

  private PrintlnStat printlnStat() {

    symbolToken.add(lexer.token);

    lexer.nextToken();

    ArrayList<Expr> exprList = null;

    exprList = exprList();



    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Esqueceu ;");
      System.exit(0);
    }
    lexer.nextToken();

    return new PrintlnStat(exprList);

  }


  private WhileStat whileStat() {
    lexer.nextToken();
    Expr e = expr();

    //verificar se a expressao é boolean
    if ( e.getType() != Type.booleanType ) {
      System.out.println("Tipo Boolean esperado na expressão while");
      System.exit(0);
    }


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
        System.exit(0);
      }

      Variable v = new Variable(ident,Type.intType);
      symbolTable.put(ident,v);

      lexer.nextToken();

      if(lexer.token != Symbol.IN) {
        System.out.println("Esperando in");
        System.exit(0);
      }
      lexer.nextToken();

      Expr startExpr = expr();
      first = lexer.getValueNumber();
      if(lexer.token != Symbol.TWODOT) {
        System.out.println("Esperando ..");
        System.exit(0);
      }
      lexer.nextToken();
      Expr endExpr = expr();
      second = lexer.getValueNumber();

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
    Expr e = expr();

    //verificar se a expressao é boolean
    if ( e.getType() != Type.booleanType ) {
      System.out.println("Tipo Boolean esperado na expressão if");
      System.exit(0);
    }

    StatList ifPart = statList();
    StatList elsePart = null;
    if(lexer.token == Symbol.ELSE){
      lexer.nextToken();
      elsePart = statList();
    }

    return new IfStat(e, ifPart, elsePart);
  }

  private StatList statList() {
    StatList statList = new StatList();

    if(lexer.token != Symbol.OPENCHAVE){
      System.out.println("Esperando {");
      System.exit(0);
    }

    lexer.nextToken();

    //{ stat }
    while (lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token == Symbol.FOR || lexer.token == Symbol.IF ||
            lexer.token == Symbol.WHILE || lexer.token == Symbol.PRINTLN || lexer.token == Symbol.PRINT) {
      statList.add(stat());
    }

    if (lexer.token != Symbol.CLOSECHAVE) {
      System.out.println("Esperado }");
      System.exit(0);
    }

    lexer.nextToken();

    return statList;
  }

  private AssignStat assignStat() {

    String name = lexer.getStringValue();

    Variable v = symbolTable.get(name);

    if(v == null) {
      System.out.println("Variavel " + name + " não foi declarada.");
      System.exit(0);
    }
    lexer.nextToken();

    symbolToken.add(lexer.token);

    if(lexer.token != Symbol.ASSIGN) {
      System.out.println("Faltando =");
      System.exit(0);
    }
    lexer.nextToken();


    Expr e = expr();

    if(lexer.token != Symbol.SEMICOLON) {
      System.out.println("Faltando ;");
      System.exit(0);
    }

    if(v.getType() != e.getType()){
      System.out.println("A variavel "+ name + " é do tipo "+ v.getType());
      System.exit(0);
    }

    lexer.nextToken();
    return new AssignStat(name, e);
  }

  private ArrayList<Expr> exprList() {
    ArrayList<Expr> expr = new ArrayList<Expr>();

    expr.add(orExpr());
    while(lexer.token == Symbol.PLUSPLUS) {
      lexer.nextToken();
      expr.add(orExpr());
    }
    return expr;
  }

  private Expr expr() {
    Expr left, right;

    Variable v = symbolTable.get(lexer.getStringValue());

    left = orExpr();
    while (lexer.token == Symbol.PLUSPLUS) {
      lexer.nextToken();
      right = orExpr();
      left = new CompositeExpr(left, Symbol.PLUSPLUS, right, symbolToken, v.getType());
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
        System.exit(0);
      }
      left = new CompositeExpr(left, Symbol.OR, right, null, null);
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
        System.exit(0);
      }
      left = new CompositeExpr(left, Symbol.AND, right,null, null);
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
        System.exit(0);
      }
      left = new CompositeExpr(left, op, right,null, null);
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
        System.out.println("Os valores da operação "+ op + " tem que ser do mesmo tipo");
        System.exit(0);
      }
      left = new CompositeExpr(left, op, right,null, null);
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
        System.out.println("Os valores da operação "+ op + " tem que ser do mesmo tipo");
        System.exit(0);
      }
      left = new CompositeExpr(left, op, right,null, null);
    }
    return left;
  }

  private Expr simpleExpr() {
    Expr e;
    switch(lexer.token) {
      case LiteralString:
        String value = lexer.getStringIdent();
        lexer.nextToken();
        return new StringExpr(value);
      case NUMBER:
        return number();
      case TRUE:
        lexer.nextToken();
        return BooleanExpr.True;
      case TYPENULL:
        return typeNull();
      case TYPENULLSTRING:
        return typeNullString();
      case FALSE:
        lexer.nextToken();
        return BooleanExpr.False;
      case NOT:
        lexer.nextToken();
        e = expr();
        if(e.getType() != Type.booleanType){
          System.out.println("Variavel tem que ser do tipo boolean para suportar o tipo !");
          System.exit(0);
        }
        return new UnaryExpr(e, Symbol.NOT);
      case PLUS:
        lexer.nextToken();
        e = expr();
        if( e.getType() != Type.intType){
          System.out.println("Variavel tem que ser do tipo int para suportar o tipo +");
          System.exit(0);
        }
        return new UnaryExpr(e, Symbol.PLUS);
      case MINUS:
        lexer.nextToken();
        e = expr();
        if( e.getType() != Type.intType){
          System.out.println("Variavel tem que ser do tipo int para suportar o tipo -");
          System.exit(0);
        }
        return new UnaryExpr(e, Symbol.MINUS);
      case LEFTPAR:
        lexer.nextToken();
        e = expr();
        if(lexer.token != Symbol.RIGHTPAR){
          System.out.println("Erro faltando )");
          System.exit(0);
        }
        symbolToken.add(lexer.token);
        lexer.nextToken();
        return e;
      default:
        if(lexer.token != Symbol.IDENT){
          System.out.println("Erro!!! Não é possivel utilizar o operador dessa forma");
          System.exit(0);
        }
        String name = lexer.getStringValue();

        Variable v = symbolTable.get(name);

        if(v == null) {
          System.out.println("Variavel " + name + " não foi declarada.");
          System.exit(0);
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
      System.exit(0);
    }

    String name = lexer.getStringValue();

    if(symbolTable.get(name) != null) {
      System.out.println("Variavel "+ name + " já foi declarada.");
      System.exit(0);
    }

    Variable v = new Variable(name, type);
    symbolTable.put(name,v);

    lexer.nextToken();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Faltando ;");
      System.exit(0);
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
        throw new IllegalStateException("Tipo não suportado");
    }
    lexer.nextToken();
    return result;
  }

  private NumberExpr number() {

    int value = lexer.getValueNumber();
    lexer.nextToken();

    return new NumberExpr(value);
  }

  private NullTypeInterger typeNull() {

    int value = lexer.getValueNumber();
    lexer.nextToken();

    return new NullTypeInterger(value);
  }

  private NullTypeString typeNullString() {

    String value = lexer.getStringIdent();
    lexer.nextToken();

    return new NullTypeString(value);
  }
}
