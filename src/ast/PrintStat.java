package ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class PrintStat extends Stat{

  private ArrayList<Expr> expr;

  public PrintStat(ArrayList<Expr> expr) {
    this.expr = expr;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print("printf (\"");

    for(Expr r: expr){
      if(r.getType() != null) {
        if (r.getType().getName() == Type.intType.getName())
          pw.print(" %d");
        else if (r.getType().getName() == Type.stringType.getName()) {
          pw.print(" %s");
        } else if (r.getType().getName() == Type.booleanType.getName()) {
          pw.print(" %d");
        }
      }else {
        r.genC(pw);
      }
    }

    pw.print("\"");


    for(Expr r: expr){

      if(r.getType() == null) {
        pw.print("");
      }else {
        pw.print(", ");
        r.genC(pw);
      }
    }
    pw.println(");");

  }

  @Override
  public void eval(Map<String, Integer> memory) {

    //System.out.println(expr.eval(memory));
  }

}
