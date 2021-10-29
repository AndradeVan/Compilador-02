package main;

/*TODO

  - Criar eval

  - Ajustar o GenC

  - Eval
    - for ok
    - if ok
    - while ok
    - println
    - composite
      - int ok
<<<<<<< HEAD
      - boolean ok
        - Operadores > < >= <= == != and e or
=======
      - boolean
        - Operadores > < >= <= == != and e or -> And e Or n�o entendi muito bem como podemos tratar
>>>>>>> 173929510450da1fb4e0cde15fd97032cda4a802
        - Quais foram: > < >= <= == !=
      - String ok
        - Operadores > < >= <= == e !=
        - Quais foram: > < == e != >= <=
    - unary
      - Operadores + - e !
      - Quais foram: !
    - varListStat
 */


import ast.Program;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String []args){
    /*char []input = ("var Int n; \r\n"
            + "var Int soma; \r\n"
            + " println \"true = \" ++ (true >= false);\r\n"
            ).toCharArray();*/
   char []input = (" var Int n;"
            + "n = 100;\r\n"
            + "var Int soma; \r\n"
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
            + "   soma = (--soma) + i*i; \r\n"
            + " } \r\n"
            + " i = i + 1; \r\n"
            + "} \r\n"
            + "var Int somaFor;"
            + "somaFor = 0;"
            + "var Int quad;"
            + "for k in 0..100 {"
            + " quad = k*k;"
            + " if k%2 == 0 {"
            + "   somaFor = somaFor + quad;"
            + " }"
            + "}\r\n"
            + "println \"soma = \" ++ soma;"
            + "println \"somaFor = \" ++ somaFor;"
            + "var Int num;"
            + "var String str;"
            + "var Boolean souVerd;"
            + "if soma == somaFor {"
            + " num = 0;"
            + " if num > 0 {"
            + "   str = \"sou String\";"
            + "   for cont in 1..5 {"
            + "     souVerd = true;"
            + "     println \"O valor dessa porra = \" ++ (souVerd ++ \" \") ++ cont;"
            + "   }"
            + "   str = \"continuo String\";"
            + "   println \"str = \" ++ str;"
            + " }"
            + " else {"
            + "   num = 1;"
            + "   println \"num = \" ++ num;"
            + " }"
            + " println \"true = \" ++ (true >= false);"
            + "}"
            + "else {"
            + " println \"Alguma coisa errada! Soma != SomaFor\";"
            + "}\r\n"
            + "if 0 < 1 && ((true >= false && \"abc\" < \"cba\") && \"A\" == \"A\") {"
            + " println \"Ufa, deu certo!\";"
            + "}\r\n"

            ).toCharArray();


    Compiler compiler = new Compiler();
    Program program = compiler.compile(input);

    Map<String, Object> memory = new HashMap<>();

      program.genC(new PrintWriter(System.out));

      program.eval(memory);


  }


}
