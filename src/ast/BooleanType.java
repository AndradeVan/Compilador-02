package ast;

import java.io.PrintWriter;

public class BooleanType extends Type {
  public BooleanType() {
      super("Boolean");
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("int");
  }
}
