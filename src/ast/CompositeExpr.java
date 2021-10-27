package ast;

import lexer.Symbol;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

   if(op == Symbol.PLUSPLUS) {
     //olhar aqqui
     return Type.stringType;
   }else if( op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE || op == Symbol.LT ||
            op == Symbol.GE || op == Symbol.GT ||
            op == Symbol.AND || op == Symbol.OR )
      return Type.booleanType;
    else
      return Type.intType;
  }

  @Override
  public void genC(PrintWriter pw) {

      left.genC(pw);

      switch (op) {
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
        case PLUSPLUS:

          break;
        case OR:
          pw.print(" || ");
          break;
      }
    right.genC(pw);
  }

  @Override
  public int eval(Map<String, Integer> memory) {
    /*if (op == Symbol.PLUS){
      return left.eval(memory) + right.eval(memory);
    }else if (op == Symbol.MINUS) {
      return left.eval(memory) - right.eval(memory);
    }else if (op == Symbol.MULT) {
      return left.eval(memory) * right.eval(memory);
    }else if (op == Symbol.DIV) {
      return left.eval(memory) / right.eval(memory);
    }else if (op == Symbol.REMAINDER) {
      return left.eval(memory) % right.eval(memory);
    }else if (op == Symbol.LT) {
      if( left.eval(memory) < right.eval(memory)) {
        return 1;
      }
      return 0;
    }
    else if (op == Symbol.GT) {
      if( left.eval(memory) > right.eval(memory)) {
        return 1;
      }
      return 0;
    }else if (op == Symbol.LE) {
      if( left.eval(memory) <= right.eval(memory)) {
        return 1;
      }
      return 0;
    } else if (op == Symbol.GE) {
      if( left.eval(memory) >= right.eval(memory)) {
        return 1;
      }
      return 0;
    } else if (op == Symbol.EQ) {
      //verificar string
      //int test = left.eval(memory);
      if(left.getType() == Type.stringType && right.getType() == Type.stringType){
        if( left.eval(memory) == right.eval(memory)) {
          return 1;
        }
      }

      return 0;
    } else if (op == Symbol.NEQ) {
      if( left.eval(memory) != right.eval(memory)) {
        return 1;
      }
      return 0;
    }else if(op == Symbol.AND) {
      if(left.eval(memory) == right.eval(memory)) {
        return 1;
      }else {
        return 0;
      }

    }else if(op == Symbol.OR) {
      boolean element = convertBoolOr(left.eval(memory), right.eval(memory));
      if(element == true)
        return 1;
      else
        return 0;
    }
    else{
      throw new RuntimeException("Erro interno em CompositeExpr.");
    }*/
    return 0;

  }

  private boolean convertBoolOr(int left, int right) {
    if(left == 0 && right == 0) {
      return false;
    }else if(left == 1 && right == 0){
      return true;
    }else if(left == 0 && right == 1){
      return true;
    }
    return true;
  }
}
