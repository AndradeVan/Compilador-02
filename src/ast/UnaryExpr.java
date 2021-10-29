package ast;

import lexer.Symbol;

import java.io.PrintWriter;
import java.util.Map;

public class UnaryExpr extends Expr{
  private Expr expr;
  private Symbol op;
  private int count;

  public UnaryExpr(Expr expr, Symbol op, int count){
    this.expr = expr;
    this.op = op;
    this.count = count;
  }

  @Override
  public Type getType() {
    return expr.getType();
  }

  @Override
  public Type getTypetoString() {
    return expr.getType();
  }

  @Override
  public void genC(PrintWriter pw) {
    switch(op) {
      case PLUS:
        pw.print("+");
        break;
      case MINUS:
        if(count % 2 == 0){
          pw.print("");
        }else
          pw.print("-");
        break;
      case NOT:
        pw.print("!");
        break;
    }
    expr.genC(pw);
  }

  @Override
  public Object eval(Map<String, Object> memory) {
    int valeuMinus = -1;
    int valuePlus = 1;
    if (op == Symbol.MINUS){
      return valeuMinus * ((Integer) expr.eval(memory));
    }else if (op == Symbol.PLUS) {
      return valuePlus * (Integer) expr.eval(memory);
    }
    else if (op == Symbol.NOT) {
      if (valeuMinus * (Integer) expr.eval(memory) != (Integer) expr.eval(memory)) {
        return 0;
      }else {
        return 1;
      }
    }
    return 1;
  }
}
