package main;

/*TODO
  - Verificar tipos, por exemplo :
      var Int a;
      a = 2;
      a = 'erro'
  - As expressoes do if e while devem ser booleanas.
  - As operacoes aritmeticas exigem operandos inteiros. Operacoes booleanas como ! e && exigem operandos booleanos.
    Considere true < false e use as operacoes de comparacoes usuais para strings.
  - ajustar o gerador de C - como faz ao declarar String, pois em C não temos String e sim char
  - Criar eval

 */


import ast.Program;

import java.io.PrintWriter;

public class Main {
  public static void main(String []args){
    char []input = ("var String n;\r\n"
            + "n = 'teste'; \r\n"
            + "var String a;"
            + "a = true ++ false ++ 'teste'; \r\n "
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
