package ast;

import java.io.PrintWriter;

public class StringType extends Type {
  public StringType() {
    super("string");
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("char *");
  }
}
