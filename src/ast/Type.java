package ast;

abstract public class Type {

  private String name;

  public Type (String name) {
    this.name = name;
  }

  public static Type booleanType = new BooleanType();
  public static Type intType = new IntType();

}
