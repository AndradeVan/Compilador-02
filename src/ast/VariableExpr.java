package ast;

import java.io.PrintWriter;
import java.util.Map;

public class VariableExpr extends Expr{

  private Variable v;

  public VariableExpr(Variable v) {
    this.v = v;
  }

  @Override
  public Type getType() {
    return v.getType();
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    int valueVariable = memory.get(v.getName());
    return valueVariable;
  }

  @Override
  public void genC(PrintWriter pw) {

    pw.print(v.getName());
  }
}
