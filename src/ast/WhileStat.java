package ast;

import java.io.PrintWriter;

public class WhileStat extends Stat{
  private Expr expr;
  private StatList statList;

  public WhileStat(Expr expr, StatList statList) {
    this.expr = expr;
    this.statList = statList;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("while (");
    expr.genC(pw);
    pw.println(") {");
    statList.genC(pw);
    pw.println("}");
  }
}
