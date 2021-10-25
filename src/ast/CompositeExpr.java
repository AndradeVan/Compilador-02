package ast;

import lexer.Symbol;

import java.io.PrintWriter;
import java.util.Map;

public class CompositeExpr extends Expr{
  private Symbol op;
  private Expr left;
  private Expr right;

  public CompositeExpr(Expr left, Symbol op, Expr right){
    this.op = op;
    this.left = left;
    this.right = right;
  }

  @Override
  public Type getType() {
    //verificar os tipos aqui
    if ( op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE || op == Symbol.LT ||
            op == Symbol.GE || op == Symbol.GT ||
            op == Symbol.AND || op == Symbol.OR )
      return Type.booleanType;
    else
      return Type.intType;
  }

  @Override
  public void genC(PrintWriter pw) {
    left.genC(pw);
    switch(op) {
      case PLUS:
        pw.print(" + ");
        break;
      case MINUS:
        pw.print(" - ");
        break;
      case DIV:
        pw.print(" / ");
        break;
      case REMAINDER:
        pw.print(" % ");
        break;
      case MULT:
        pw.print(" * ");
        break;
      case LT:
        pw.print(" < ");
        break;
      case LE:
        pw.print(" <= ");
        break;
      case GT:
        pw.print(" > ");
        break;
      case GE:
        pw.print(" => ");
        break;
      case NEQ:
        pw.print(" != ");
        break;
      case EQ:
        pw.print(" == ");
        break;
      case AND:
        pw.print(" && ");
        break;
      case OR:
        pw.print(" || ");
        break;
      case PLUSPLUS:
        pw.print(" ++ ");
        break;
    }
    right.genC(pw);
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    return 0;
  }
}
