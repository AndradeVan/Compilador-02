package ast;

import java.io.PrintWriter;

abstract public class Expr {
  abstract public Type getType();
  public abstract void genC(PrintWriter pw);
}
