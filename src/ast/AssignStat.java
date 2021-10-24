package ast;

import java.io.PrintWriter;

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
}
