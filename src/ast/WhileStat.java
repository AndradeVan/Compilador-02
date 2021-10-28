package ast;

import java.io.PrintWriter;
import java.util.Map;

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

  @Override
  public void eval(Map<String, Object> memory) {
    Object element = expr.eval(memory);
    boolean bool = convertBool(1);
    while(bool) {
      statList.eval(memory);
      element = expr.eval(memory);
      bool = convertBool(1);
    }
  }

  private boolean convertBool(int element) {
    if(element == 1){
      return true;
    }
    return false;
  }

}
