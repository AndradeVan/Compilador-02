package ast;

import java.io.PrintWriter;

public class IfStat extends Stat{
  private Expr expr;
  private StatList ifPart;
  private StatList elsePart;

  public IfStat(Expr expr, StatList ifPart, StatList elsePart) {
    this.expr = expr;
    this.ifPart = ifPart;
    this.elsePart = elsePart;
  }

  @Override
  public void genC(PrintWriter pw){
    pw.print("if ( ");
    expr.genC(pw);
    pw.println(" ) {");
    ifPart.genC(pw);
    pw.println("}");
    if( elsePart != null) {
      pw.println("else {");
      elsePart.genC(pw);
      pw.println("}");
    }
  }
}
