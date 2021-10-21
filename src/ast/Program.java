package ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Program {

  private List<Stat> statList;
  //private ArrayList<Variable> arrayVariable;

  public Program(List<Stat> statList) {
    this.statList = statList;
    //this.arrayVariable = arrayVariable;
  }

  public void genC(PrintWriter pw) {
    pw.println("#include <stdio.h>");
    pw.println("void main() {");

    /*for( Variable v : arrayVariable )
      pw.println(v.getType() +
              " " + v.getName() + ";" );*/

    for ( Stat stat : statList) {
      stat.genC(pw);
    }

    pw.println("}");
    pw.flush();
  }
}
