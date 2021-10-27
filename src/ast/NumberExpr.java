package ast;

import java.io.PrintWriter;
import java.util.Map;

public class NumberExpr extends Expr{
  private int value;

  public NumberExpr(int value) {
    this.value = value;
  }

  public int getValue() { return value; }

  @Override
  public Type getType() {
    return Type.intType;
  }


  @Override
  public void genC(PrintWriter pw) {
    pw.print(value);
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    return value;
  }
}
