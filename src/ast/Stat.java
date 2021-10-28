package ast;

import java.io.PrintWriter;
import java.util.Map;

abstract public class Stat {
  public abstract  void genC(PrintWriter pw);
  public abstract void eval(Map<String, Object> memory);

}
