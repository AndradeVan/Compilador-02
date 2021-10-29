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

  //Utilizando só para verificar operadores com string
  @Override
  public Type getTypetoString() {
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
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" < ");
          if(left.getTypetoString() == Type.stringType) {
          }
          break;
        case LE:
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" <= ");
          if(left.getTypetoString() == Type.stringType) {
          }
          break;
        case GT:
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" > ");
          if(left.getTypetoString() == Type.stringType) {
          }
          break;
        case GE:
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" >= ");
          if(left.getTypetoString() == Type.stringType) {
          }
          break;
        case NEQ:
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" != ");
          if(left.getTypetoString() == Type.stringType) {
          }
          break;
        case EQ:
          if(left.getTypetoString() == Type.stringType) {
          }
          pw.print(" == ");
          if(left.getTypetoString() == Type.stringType) {
          }
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
      System.out.println("left -->" + left.eval(memory) );
      System.out.println("right -->" + right.eval(memory) );
      return (Integer) left.eval(memory) - (Integer) right.eval(memory);
    } else if (op == Symbol.MULT) {
      return (Integer) left.eval(memory) * (Integer) right.eval(memory);
    } else if (op == Symbol.DIV) {
      return (Integer) left.eval(memory) / (Integer) right.eval(memory);
    } else if (op == Symbol.REMAINDER) {
      return (Integer) left.eval(memory) % (Integer) right.eval(memory);
    }

    else if (op == Symbol.LT) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory), op);
      }else {
        if ((Integer) left.eval(memory) < (Integer) right.eval(memory)) {
          return 1;
        }
        return 0;
      }
    }else if (op == Symbol.GT) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory),op);
      }else {
        if ((Integer) left.eval(memory) > (Integer) right.eval(memory)) {
          return 1;
        }
        return 0;
      }
    }else if (op == Symbol.LE) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory),op);
      }else {
        if ((Integer) left.eval(memory) <= (Integer) right.eval(memory)) {
          return 1;
        }
      }
      return 0;
    } else if (op == Symbol.GE) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory),op);
      }else {
        if ((Integer) left.eval(memory) >= (Integer) right.eval(memory)) {
          return 1;
        }
      }
      return 0;
    }else if (op == Symbol.EQ) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory),op);
      }else {
        System.out.println("Valooooor" + left.eval(memory));
        System.out.println("Teste" + right.eval(memory));
        if ((Integer) left.eval(memory) == (Integer) right.eval(memory)) {
          return 1;
        }
        return 0;
      }

    } else if (op == Symbol.NEQ) {
      if(left.getTypetoString() == Type.stringType && right.getTypetoString() == Type.stringType) {
        return verificarString((String) left.eval(memory), (String) right.eval(memory),op);
      }else {
        if ((Integer) left.eval(memory) != (Integer) right.eval(memory)) {
          return 1;
        }
        return 0;
      }
    }

    else if(op == Symbol.AND) {
      boolean element = convertBoolAnd((Integer) left.eval(memory),(Integer) right.eval(memory));
      if(element) {
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
    }else if(op == Symbol.PLUSPLUS) {
      String leftValue = (String) left.eval(memory).toString();
      String rightValue = (String) right.eval(memory).toString();

      String valueConcat = leftValue.concat(rightValue);

     return valueConcat;
    }
    else{
      throw new RuntimeException("Erro interno em CompositeExpr.");
    }

  }

  private int verificarString(String left, String right, Symbol op) {
    //< || <=
    if(op == Symbol.LT || op == Symbol.LE) {
      if(left.compareTo(right) <= 0) {
        return 1;
      }
      return 0;
    }
    // > || >=
    else if(op == Symbol.GT || op == Symbol.GE){
      if(left.compareTo(right) >= 0) {
        return 1;
      }
      return 0;
    }

    else if(op == Symbol.EQ) {
      if(left.equals(right)) {
        return 1;
      }else{
        return 0;
      }
    }
    else if(op == Symbol.NEQ) {
      if (!left.equals(right)) {
        return 1;
      } else {
        return 0;
      }
    }
    return 0;
  }

  private boolean convertBoolAnd(int left, int right) {
    if(left == 1 && right == 1) {
      return true;
    }else {
      return false;
    }
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
