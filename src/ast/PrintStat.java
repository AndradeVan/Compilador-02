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
  public void eval(Map<String, Object> memory) {

    for(Expr stat : expr){
      String bool = (String) stat.eval(memory).toString();
      if(stat.getType() == Type.booleanType) {
        int convert = Integer.parseInt(bool);
        if (convert == 1) {
          System.out.print("true");
        } else {
          System.out.print("false");
        }
      }else{
        System.out.print(stat.eval(memory));
      }
      //System.out.print(" ");
    }
  }
}
