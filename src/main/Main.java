package main;

/*TODO

  - Ajustar aspas duplas inves de aspa simples

  - Criar eval
  - Verificar os comentários em conjunto
  - Verificar escopo das variaveis ?
  - Ajustar valores de false e true para imprimir - GenC
  - Ajustar o ++ no genC

 */


import ast.Program;

import java.io.PrintWriter;

public class Main {
  public static void main(String []args){
    char []input = (
            "var Int n;"
            + "var Int i;"
            + "var Boolean verd;"
            + "if 'abc' < 'cba' && 'A' == 'A'  {\r\n"
            + "}\r\n"
            + "else {"
            + "}\r\n"
            //+ "if !!verd {"
            //+ " var Int i;"
            //+ " i = 0;"
            //+ "while i <= n && !!verd {" --> Verificar isso

            //+ "}\r\n"
            //+ " i = i + 1;\r\n"

            /*"var Int a;"
            + "var Int b;"
            + "if a%2 == 2 {"
            + " var String i;"
            + " i = 0;"
            + "}"
            + "i = i + 1;\r\n"*/
            //== (0+0*0/1)
            /*+ "var Int soma; \r\n"
            + "soma = 0; \r\n"
            + "var Int i; \r\n"
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
            //+ "   soma = i*i; \r\n"
            + "   soma = (--soma) + i*i; \r\n"
            + " } \r\n"
            + " i = i + 1; \r\n"
            + "} \r\n"*/
            ).toCharArray();


    Compiler compiler = new Compiler();
    Program program = compiler.compile(input);

    //program.genC(new PrintWriter(System.out));
  }


}
