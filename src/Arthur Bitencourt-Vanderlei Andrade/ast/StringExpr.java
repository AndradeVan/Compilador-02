package ast;

import java.io.PrintWriter;
import java.util.Map;

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
  public Type getTypetoString() {
    return Type.stringType;
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
