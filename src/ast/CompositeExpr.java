package ast;

import lexer.Symbol;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class CompositeExpr extends Expr{
  private Symbol op;
  private Expr left;
  private Expr right;
  private ArrayList<Symbol> listSymbol;
  private Type typeVar;

  public CompositeExpr(Expr left, Symbol op, Expr right, ArrayList<Symbol> listSymbol, Type typeVar){
    this.op = op;
    this.left = left;
    this.right = right;
    this.listSymbol = listSymbol;
    this.typeVar = typeVar;
  }

  @Override
  public Type getType() {

   if(listSymbol != null) {
     if ((listSymbol.contains(Symbol.PRINTLN) == true || listSymbol.contains(Symbol.PRINT) == true)
             && op == Symbol.PLUSPLUS) {
       return typeVar;
     }
   }else {
     if (op == Symbol.PLUSPLUS) {
       return Type.stringType;
     } else if (op == Symbol.EQ || op == Symbol.NEQ || op == Symbol.LE || op == Symbol.LT ||
             op == Symbol.GE || op == Symbol.GT ||
             op == Symbol.AND || op == Symbol.OR) {
       return Type.booleanType;
     }else {
       return Type.intType;
     }
   }
   return Type.stringType;
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
          pw.print(" >= ");
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
  public Object eval(Map<String, Object> memory) {
    if (op == Symbol.PLUS) {
      return (Integer) left.eval(memory) + (Integer) right.eval(memory);
    } else if (op == Symbol.MINUS) {
      return (Integer) left.eval(memory) - (Integer) right.eval(memory);
    } else if (op == Symbol.MULT) {
      return (Integer) left.eval(memory) * (Integer) right.eval(memory);
    } else if (op == Symbol.DIV) {
      return (Integer) left.eval(memory) / (Integer) right.eval(memory);
    } else if (op == Symbol.REMAINDER) {
      return (Integer) left.eval(memory) % (Integer) right.eval(memory);
    } else if (op == Symbol.LT) {
      if (left.getType() == Type.booleanType && right.getType() == Type.booleanType) {
        //errado
        if ((Integer) left.eval(memory) < (Integer) right.eval(memory)) {
          return "true";
        }
      } else if (left.getType() == Type.intType && right.getType() == Type.intType) {
        if ((Integer) left.eval(memory) < (Integer) right.eval(memory)) {
          return 1;
        }
      } else if (left.getType() == Type.stringType && right.getType() == Type.stringType) {
        if ((Integer) left.eval(memory) < (Integer) right.eval(memory)) {
          return "teste";
        }
      }
    }else if (op == Symbol.GT) {
      if( (Integer) left.eval(memory) > (Integer) right.eval(memory)) {
        return 1;
      }
      return 0;
    }else if (op == Symbol.LE) {
      if( (Integer) left.eval(memory) <= (Integer) right.eval(memory)) {
        return 1;
      }
      return 0;
    } else if (op == Symbol.GE) {
      if( (Integer) left.eval(memory) >= (Integer) right.eval(memory)) {
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
      boolean element = convertBoolOr( (Integer) left.eval(memory),(Integer) right.eval(memory));
      if(element == true)
        return 1;
      else
        return 0;
    }
    else{
      throw new RuntimeException("Erro interno em CompositeExpr.");
    }
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
