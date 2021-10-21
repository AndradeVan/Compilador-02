package ast;

import java.io.PrintWriter;

abstract public class Stat {
  public abstract  void genC(PrintWriter pw);

}
