package ast;

import java.io.PrintWriter;
import java.util.Map;

public class AssignStat extends Stat{
  private String ident;
  private Expr expr;

  public AssignStat(String ident, Expr expr) {
    this.ident = ident;
    this.expr = expr;
  }
  @Override
  public void genC(PrintWriter pw) {

    pw.print(ident + " = ");

//    if(expr.getType() == Type.stringType) {
//      pw.print("\"");
//    }
//    pw.println(expr.getClass());
//    if(expr.getClass() == CompositeExpr.class) {
//    	
//    }
    expr.genC(pw);
    if(expr.getType() == Type.stringType) {
      pw.print("\"");
    }
    pw.println(";");
  }

  @Override
  public void eval(Map<String, Object> memory) {
    Object e = expr.eval(memory);
    memory.put("" + ident, e);
  }
}
