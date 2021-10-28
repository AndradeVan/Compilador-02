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
    if(expr.getType() == Type.stringType)
      pw.print("\"");
    expr.genC(pw);
    if(expr.getType() == Type.stringType)
      pw.print("\"");
    pw.println(") {");
    statList.genC(pw);
    pw.println("}");
  }

  @Override
  public void eval(Map<String, Object> memory) {
    Object element = expr.eval(memory);
    boolean bool = convertBool((Integer) element);
    while(bool) {
      statList.eval(memory);
      element = expr.eval(memory);
      bool = convertBool((Integer) element);
    }
  }

  private boolean convertBool(int element) {
    if(element == 1){
      return true;
    }
    return false;
  }

}
