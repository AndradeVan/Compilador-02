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
  public Type getTypetoString() {
    return Type.intType;
  }


  @Override
  public void genC(PrintWriter pw) {
    pw.print(value);
  }

  @Override
  public Object eval(Map<String, Object> memory) {
    return value;
  }
}
