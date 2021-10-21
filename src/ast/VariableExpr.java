package ast;

import java.io.PrintWriter;

public class VariableExpr extends Expr{

  private Variable v;

  public VariableExpr(Variable v) {
    this.v = v;
  }

  @Override
  public Type getType() {
    return null;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print(v.getName());
  }
}
