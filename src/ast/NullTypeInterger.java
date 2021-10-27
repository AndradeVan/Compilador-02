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
  public void genC(PrintWriter pw) {
      pw.print(value);
    }


  @Override
  public int eval(Map<String, Integer> memory) {
    return 0;
  }
}

