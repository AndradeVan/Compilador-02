package ast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatList {

  private List<Stat> statList = new ArrayList<Stat>();

  public void add(Stat stat){
    statList.add(stat);
  }

  public void eval(Map<String, Integer> memory) {
    for(Stat stat : statList){
      stat.eval(memory);
    }
  }

  public void genC(PrintWriter pw) {
    for(Stat stat : statList){
      stat.genC(pw);
    }
  }
}
