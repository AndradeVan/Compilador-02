package ast;

import java.io.PrintWriter;
import java.util.Map;

abstract public class Expr {
  public abstract Type getType();
  public abstract void genC(PrintWriter pw);
  public abstract Object eval(Map<String, Object> memory);

}
