package ast;

import java.io.PrintWriter;
import java.util.Map;

abstract public class Expr {
  abstract public Type getType();
  public abstract void genC(PrintWriter pw);
  public abstract int eval(Map<String, Integer> memory);

}
