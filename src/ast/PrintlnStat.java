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
//	pw.print(expr.getClass());
    pw.print("printf(\"");
    
	if(expr.getClass() == CompositeExpr.class) {
		CompositeExpr cexpr = (CompositeExpr)expr;
		if(cexpr.getClass() == CompositeExpr.class) {
			cexpr = (CompositeExpr)cexpr.getLeft();
		}
//		pw.println(cexpr.getLeft().getType());
		if(cexpr.getLeft().getType().equals(Type.intType)) {
			pw.print("%d ");
			cexpr.getLeft().genC(pw);
//			cexpr.getRight().genC(pw);
//			pw.println(cexpr.getLeft());
//			pw.println(cexpr.getRight());
		}
		else
		if(cexpr.getLeft().getType().equals(Type.booleanType)) {
			pw.print("%d ");
			cexpr.getLeft().genC(pw);
//			pw.print("\"");
//			
		}else
		if(cexpr.getLeft().getType().equals(Type.stringType)) {
			pw.print("%s ");
			cexpr.getLeft().genC(pw);
		}
//		
		if(cexpr.getRight().getType().equals(Type.intType)) {
			pw.print("%d\", ");
			cexpr.getRight().genC(pw);
		}else
		if(cexpr.getRight().getType().equals(Type.booleanType)) {
			cexpr.getRight().genC(pw);
			pw.print("\",");
			
		}else
		if(cexpr.getRight().getType().equals(Type.stringType)) {
			pw.print("%s\", ");
			cexpr.getRight().genC(pw);
		}
		
//		pw.print((CompositeExpr)expr.getLeft())
	} else {
		
		if(expr.getType().equals(Type.intType)) {
			pw.print("%d\", ");
			expr.genC(pw);
		} else	
		if(expr.getType().equals(Type.booleanType)) {
			expr.genC(pw);
			pw.print("\"");
			
		} else	
		if(expr.getType().equals(Type.stringType)) {
			pw.print("%s\", ");
			expr.genC(pw);
		}
	}
//    expr.genC(pw);
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
