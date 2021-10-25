package ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {

  private List<Stat> statList;

  public Program(List<Stat> statList) {
    this.statList = statList;
  }

  public void genC(PrintWriter pw) {
    pw.println("#include <stdio.h>");
    pw.println("void main() {");

    for ( Stat stat : statList) {
      stat.genC(pw);
    }

    pw.println("}");
    pw.flush();
  }

  public void eval(Map<String, Integer> memory) {
    for ( Stat stat : statList) {
      stat.eval(memory);
    }
  }
}
