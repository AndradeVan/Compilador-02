package ast;

import java.io.PrintWriter;

public class ForStat extends Stat{
  private String ident;
  private Expr first;
  private Expr second;
  private StatList statList;

  public ForStat(String ident, Expr first, Expr second, StatList statList) {
    this.ident = ident;
    this.first = first;
    this.second = second;
    this.statList = statList;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("for ( " + ident + " = " );
    first.genC(pw);
    pw.print("; " + ident + " <= ");
    second.genC(pw);
    pw.println(";" + ident +"++ ) {");
    statList.genC(pw);
    pw.println("}");
  }
}
