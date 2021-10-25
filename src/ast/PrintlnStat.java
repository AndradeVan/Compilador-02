package ast;

import java.io.PrintWriter;
import java.util.Map;

public class PrintlnStat extends Stat{
  private Expr expr;

  public PrintlnStat(Expr expr) {
    this.expr = expr;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("printf(\"%d\", ");
    expr.genC(pw);
    pw.println(");");
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    int valueBoolean = expr.eval(memory);

    System.out.println(convertBool(valueBoolean) ? "true" : "false");
  }

  private boolean convertBool(int element) {
    if(element == 1){
      return true;
    }
    return false;
  }
}
