package ast;

import java.io.PrintWriter;

public class BooleanExpr extends Expr{

  private boolean value;
  public static BooleanExpr True = new BooleanExpr(true);
  public static BooleanExpr False = new BooleanExpr(false);

  public BooleanExpr( boolean value) {
    this.value = value;
  }

  @Override
  public Type getType() {
    return Type.booleanType;
  }

  @Override
  public void genC(PrintWriter pw) {
    pw.print( value ? "1" : "0" );
  }


}
