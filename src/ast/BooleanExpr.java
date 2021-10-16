package ast;

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


}
