package ast;

import java.io.PrintWriter;
import java.util.Map;

public class NullTypeString extends Expr{
  private String value;

  public NullTypeString(String value) {
    this.value = value;
  }

  public String getValue() { return value; }

  @Override
  public Type getType() {
    return null;
  }


  @Override
  public void genC(PrintWriter pw) {
    pw.print(value);
  }


  @Override
  public int eval(Map<String, Integer> memory) {
    return 0;
  }
}
