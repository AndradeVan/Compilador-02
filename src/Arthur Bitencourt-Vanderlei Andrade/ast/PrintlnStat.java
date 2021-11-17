package ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class PrintlnStat extends Stat{
  private ArrayList<Expr> expr;
	
  public PrintlnStat(ArrayList<Expr> expr) {

    this.expr = expr;
  }

  @Override
  public void genC(PrintWriter pw) {

		pw.print("printf (\"");

		for (Expr r : expr) {
			if (r.getType() != null) {
				if (r.getType().getName() == Type.intType.getName())
					pw.print(" %d");
				else if (r.getType().getName() == Type.stringType.getName()) {
					pw.print(" %s");
				} else if (r.getType().getName() == Type.booleanType.getName()) {
					pw.print(" %d");
				}
			} else {
				r.genC(pw);
			}
		}

		pw.print("\\n\"");

		for (Expr r : expr) {

			if (r.getType() == null) {
				pw.print("");
			} else {
				pw.print(", ");
				r.genC(pw);
			}
		}
		pw.println(");");
	}
		/*
//	pw.print(expr.getClass());
    pw.print("printf(\"");
    
	if(expr.getClass() == CompositeExpr.class) {
		if(((CompositeExpr) expr).getLeft().getType().equals(Type.intType)) {
			pw.print("%d ");
//			expr.genC(pw);
		}else
		if(((CompositeExpr) expr).getLeft().getType().equals(Type.booleanType)) {
			pw.print("%d ");
//			expr.genC(pw);
//			pw.print("\"");
			
		}else
		if(((CompositeExpr) expr).getLeft().getType().equals(Type.stringType)) {
			pw.print("%s ");
			expr.genC(pw);
		}
		
		if(((CompositeExpr) expr).getRight().getType().equals(Type.intType)) {
			pw.print("%d\", ");
			expr.genC(pw);
		}else
		if(((CompositeExpr) expr).getRight().getType().equals(Type.booleanType)) {
			expr.genC(pw);
			pw.print("\",");
			
		}else
		if(((CompositeExpr) expr).getRight().getType().equals(Type.stringType)) {
			pw.print("%s\", ");
			expr.genC(pw);
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
>>>>>>> 8de23fc7a5d157ee7a5c60c430266d37f3b7ef34
    pw.println(");");

  }*/

  @Override
  public void eval(Map<String, Object> memory) {
		for(Expr stat : expr){

			if(stat.getType() == Type.booleanType){
				String bool = (String) stat.eval(memory).toString();
				int convert = Integer.parseInt(bool);
				if(convert == 1){
					System.out.print("true");
				}else{
					System.out.print("false");
				}
			}else {
				System.out.print(stat.eval(memory));
			}
			//System.out.print(" ");
		}
		System.out.println("");
  }

}
