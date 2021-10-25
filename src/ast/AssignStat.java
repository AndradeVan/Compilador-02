package ast;

import java.io.PrintWriter;
import java.util.Map;

public class AssignStat extends Stat{
  private String ident;
  private Expr expr;

  public AssignStat(String ident, Expr expr) {
    this.ident = ident;
    this.expr = expr;
  }
  @Override
  public void genC(PrintWriter pw) {

    pw.print(ident + " = ");
    expr.genC(pw);
    pw.println(";");
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    int e = expr.eval(memory);
    memory.put("" + ident, e);
  }
}
