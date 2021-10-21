package main;

/*
*

* */


import ast.Program;

import java.io.PrintWriter;

public class Main {
  public static void main(String []args){
    char []input = ("var String n;\r\n"
            + "n = 100; \r\n"
            + "var Int soma; \r\n"
            + "soma = 0; \r\n"
            /*+ "var Int i; \r\n"
            + "i = 0; \r\n"
            + "var Boolean verd; \r\n"
            + "verd = true; \r\n"
            + "var Int kk; \r\n"
            + "kk = 10000; \r\n"
            + "var Int kkWhile; \r\n"
            + "var Int m; \r\n"
            + "while i <= n && !!verd { \r\n"
            + " kkWhile = i%2; \r\n"
            + " m = (0+0*0/1); \r\n"
            + " if kkWhile == m { \r\n"
            + "   soma = (--soma) + i*i; \r\n"
            + " } \r\n"
            + " i = i + 1; \r\n"
            + "} \r\n"*/
            ).toCharArray();


    Compiler compiler = new Compiler();
    Program program = compiler.compile(input);

    program.genC(new PrintWriter(System.out));
  }


}
