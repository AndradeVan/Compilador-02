package ast;

import java.io.PrintWriter;

public class StringExpr extends Expr{
  private String value;

  public StringExpr(String value) {
    this.value = value;
  }
  @Override
  public Type getType() {
    return Type.stringType;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("'" + value + "'");
  }
}
