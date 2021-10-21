package ast;

import java.io.PrintWriter;

public class PrintStat extends Stat{

  private Expr expr;

  public PrintStat(Expr expr) {
    this.expr = expr;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("printf(\"%d\", ");
    expr.genC(pw);
    pw.println(");");
  }
}
