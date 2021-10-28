package ast;

import java.io.PrintWriter;
import java.util.Map;

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

  @Override
  public void eval(Map<String, Object> memory) {
    Object element = expr.eval(memory);

    boolean bool = true;

    if(bool){
      this.ifPart.eval(memory);
    } else {
      if(elsePart != null) {
        this.elsePart.eval(memory);
      }
    }
  }

  private boolean convertBool(int element) {
    if(element == 1){
      return true;
    }
    return false;
  }
}
