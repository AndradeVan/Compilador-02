package ast;

import java.io.PrintWriter;
import java.util.Map;

public class NullTypeInterger extends Expr{

  private int value;

  public NullTypeInterger(int value) {
    this.value = value;
  }

  public int getValue() { return value; }

  @Override
  public Type getType() {
    return null;
  }

  @Override
  public Type getTypetoString() {
    return null;
  }

  @Override
  public void genC(PrintWriter pw) {
      pw.print(value);
    }


  @Override
  public Object eval(Map<String, Object> memory) {
    return 0;
  }
}

