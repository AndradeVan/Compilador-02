package ast;

import java.io.PrintWriter;
import java.util.Map;

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
    pw.print("for (int " + ident + " = " );
    first.genC(pw);
    pw.print("; " + ident + " <= ");
    second.genC(pw);
    pw.println(";" + ident +"++ ) {");
    statList.genC(pw);
    pw.println("}");
  }

  @Override
  public void eval(Map<String, Integer> memory) {
    int firstValue = first.eval(memory);
    int secondValue = second.eval(memory);
    for (int i = firstValue; i < secondValue; i++) {
      memory.put("" + ident, i);
      statList.eval(memory);
    }
  }
}
