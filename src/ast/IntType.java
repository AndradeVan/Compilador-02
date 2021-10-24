package ast;

import java.io.PrintWriter;

public class IntType extends Type {
  public IntType() {
    super("Int");
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("int ");
  }
}
