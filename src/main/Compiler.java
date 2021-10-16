package main;

import ast.Type;
import ast.Variable;
import lexer.Lexer;
import lexer.Symbol;

import java.util.Hashtable;

public class Compiler {

  private Lexer lexer;
  private Hashtable<String, Variable> symbolTable;

  public void compile( char []input){
    lexer = new Lexer(input);
    symbolTable = new Hashtable<>();
    lexer.nextToken();

    program();

  }

  private void program() {

    while(lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token ==  Symbol.IF || lexer.token == Symbol.FOR
    || lexer.token == Symbol.WHILE) {
      stat();
    }
  }

  private void stat() {
    switch (lexer.token) {
      case VAR:
        varList();
        break;
      case IDENT:
        assignStat();
        break;
      case IF:
        ifStat();
        break;
      case FOR:
        forStat();
        break;
      case WHILE:
        whileStat();
        break;
    }
  }

  private void whileStat() {
    lexer.nextToken();
    expr();
    statList();
  }

  private void forStat() {
    int first, second;
    lexer.nextToken();
    if(lexer.token == Symbol.IDENT) {
      String ident = lexer.getStringValue();

      //adiciona variavel no escopo do for
      if( symbolTable.get(ident) != null) {
        System.out.println("Variavel " + ident + " já foi declarada.");
      }

      Variable v = new Variable(ident);
      symbolTable.put(ident,v);

      lexer.nextToken();

      if(lexer.token != Symbol.IN) {
        System.out.println("Esperando in");
      }
      lexer.nextToken();
      expr();
      first = lexer.getValueNumber();
      if(lexer.token != Symbol.TWODOT) {
        System.out.println("Esperando ..");
      }
      lexer.nextToken();
      expr();
      second = lexer.getValueNumber();

      //isso tá errado, pq podemos declarar uma variavel a = 1 e usar a variavel e nao o numero
      //verificar se o primeiro elemento é maior que o segundo
      if(first > second) {
        System.out.println("Erro de execução");
        System.exit(0);
      }
      statList();
      //remover a variavel do escopo do for
      symbolTable.remove(ident, v);
    }
  }

  private void ifStat() {
    lexer.nextToken();
    //verificar se a expressao é boolean

    expr();
    statList();
    if(lexer.token == Symbol.ELSE){
      //System.out.println("Esperando Else");
      lexer.nextToken();
    }
    lexer.nextToken();
  }

  private void statList() {
    if(lexer.token != Symbol.OPENCHAVE){
      System.out.println("Esperando {");
    }
    lexer.nextToken();

    //{ stat }
    while (lexer.token == Symbol.VAR || lexer.token == Symbol.IDENT || lexer.token == Symbol.FOR || lexer.token == Symbol.IF ||
            lexer.token == Symbol.WHILE || lexer.token == Symbol.PRINTLN || lexer.token == Symbol.PRINT) {
      stat();
    }

    if(lexer.token != Symbol.CLOSECHAVE) {
      System.out.println("Esperando }");
    }
    lexer.nextToken();
  }

  private void assignStat() {

    String name = lexer.getStringValue();

    //verificar se isso ta certo !?
    //Verificar se a variavel não foi declarada
    Variable v = symbolTable.get(name);
    if(v == null) {
      System.out.println("Variavel " + name + " não foi declarada.");
    }
    lexer.nextToken();

    if(lexer.token != Symbol.ASSIGN) {
      System.out.println("Faltando =");
    }
    lexer.nextToken();
    expr();
    if(lexer.token != Symbol.SEMICOLON) {
      System.out.println("Faltando ;");
    }
    lexer.nextToken();
  }

  private void expr() {
    orExpr();
    while(lexer.token == Symbol.PLUSPLUS) {
      lexer.nextToken();
      orExpr();
    }
  }

  private void orExpr() {
    andExpr();
    if (lexer.token == Symbol.OR) {
      lexer.nextToken();
      andExpr();
    }
  }

  private void andExpr() {
    relExpr();
    if(lexer.token == Symbol.AND){
      lexer.nextToken();
      relExpr();
    }
  }

  private void relExpr() {
    addExpr();
    Symbol op = lexer.token;
    if(op == Symbol.LT || op == Symbol.LE || op == Symbol.GE || op == Symbol.GT ||
            op == Symbol.EQ || op == Symbol.NEQ) {
      lexer.nextToken();
      addExpr();
    }
  }

  private void addExpr() {
    multExpr();
    Symbol op;
    while((op = lexer.token) == Symbol.PLUS || op == Symbol.MINUS) {
      lexer.nextToken();
      multExpr();
    }
  }

  private void multExpr() {
    simpleExpr();
    Symbol op;
    while((op = lexer.token) == Symbol.MULT || op == Symbol.DIV || op == Symbol.REMAINDER) {
      lexer.nextToken();
      simpleExpr();
    }
  }

  private void simpleExpr() {
    switch(lexer.token) {
      case Boolean:
        lexer.nextToken();
        break;
      case NUMBER:
        lexer.nextToken();
        break;
      case TRUE:
        lexer.nextToken();
        break;
      case FALSE:
        lexer.nextToken();
        break;
      case NOT:
        lexer.nextToken();
        expr();
        break;
      case PLUS:
        lexer.nextToken();
        expr();
        break;
      case MINUS:
        lexer.nextToken();
        expr();
        break;
      case PLUSPLUS:
        lexer.nextToken();
        expr();
        break;
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
    }
  }

  private void varList() {
    lexer.nextToken();
    type();
    if(lexer.token != Symbol.IDENT) {
      System.out.println("Erro ao criar uma variavel");
    }

    String name = lexer.getStringValue();

    if(symbolTable.get(name) != null) {
      System.out.println("Variavel "+ name + " já foi declarada.");
    }

    Variable v = new Variable(name);
    symbolTable.put(name,v);

    lexer.nextToken();
    if(lexer.token != Symbol.SEMICOLON){
      System.out.println("Faltando ;");
    }
    lexer.nextToken();
  }

  private Type type() {
    Type result;
    switch(lexer.token) {
      case Boolean:
        result = Type.booleanType;
        System.out.println("Boolean");
        break;
      case Int:
        result = Type.intType;
        System.out.println("Int");
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + lexer.token);
    }
    lexer.nextToken();
    return result;
  }
}
