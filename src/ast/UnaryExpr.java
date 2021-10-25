package ast;

import lexer.Symbol;

import java.io.PrintWriter;
import java.util.Map;

public class UnaryExpr extends Expr{
  private Expr expr;
  private Symbol op;

  public UnaryExpr(Expr expr, Symbol op){
    this.expr = expr;
    this.op = op;
  }

  @Override
  public Type getType() {
    return expr.getType();
  }

  @Override
  public void genC(PrintWriter pw) {
    switch(op) {
      case PLUS:
        pw.print("+");
        break;
      case MINUS:
        pw.print("-");
        break;
      case NOT:
        pw.print("!");
        break;
    }
    expr.genC(pw);
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    return 0;
  }
}
