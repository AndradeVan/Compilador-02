package ast;

import java.io.PrintWriter;

public class NumberExpr extends Expr{
  private int value;

  public NumberExpr(int value) { this.value = value; }

  public int getValue() { return value; }

  @Override
  public Type getType() {
    return null;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print(value);
  }
}
