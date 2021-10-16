package main;

public class Main {
  public static void main(String []args){
    char []input = ("var Int n;\r\n"
            + "n = 1; \r\n"
            + "if n != 2 || n%2 == 2 { \r\n"
            + " n = n + 1; \r\n"
            + "} \r\n"
            ).toCharArray();

    Compiler compiler = new Compiler();
    compiler.compile(input);
  }


}
