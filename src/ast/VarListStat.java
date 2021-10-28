package ast;

import java.io.PrintWriter;
import java.util.Map;

public class VarListStat extends Stat{

  private Type type;
  private String ident;

  public VarListStat(Type type, String ident) {
    this.type = type;
    this.ident = ident;
  }

  @Override
  public void genC(PrintWriter pw) {
    type.genC(pw);
    pw.print(ident);
    pw.println(";");
  }

  @Override
  public void eval(Map<String, Object> memory) {

  }
}
