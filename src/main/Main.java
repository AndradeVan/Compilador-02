package main;

/*TODO

  - Criar eval

  - Ajustar o GenC
    plus plus

  - Eval
    - for ok
    - if ok
    - while ok, mas podemos conferir com ++
    - prints
    - composite
      - int ok
      - boolean
        - Operadores > < >= <= == != and e or -> And e Or não entendi muito bem como podemos tratar
        - Quais foram: > < >= <= == !=
      - String ok
        - Operadores > < >= <= == e !=
        - Quais foram: > < == e != >= <=
    - unary
      - Operadores + - e ! ---> Verificar --- e +++
      - Quais foram: !
    - varListStat
 */


import ast.Program;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String []args){


    /*char []input = ("var Int n; "
            + "var String a;"
            + "var Int valor;"
            /*+ "var Boolean i;"
            + "var Int soma;"
            + "a = \"teste\" ++ 1 ++ false; "
            + "soma = 0;"
            + "i = false;"
            + "n = 2 + 4 - 6;"
            + "println \"O valor do boolean é: \" ++ i;"
            + "\r\n"
            + "println \"valor da soma = \" ++ soma;\r\n"
            + "n = 1;"
            + "println valor;"
            + "if \"abc\" >= \"abc\" {"
            + "println \"Um valor qualquer\";"
            + "}else{"
            + "println \"Valor do else\";"
            +"}\r\n"
            + "println \"true = \" ++ (\"abc\" < \"cba\");\r\n"
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
            + "     println \"O valor dessa porra é = \" ++ (souVerd ++ \" \") ++ cont;"
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
            //+ "if 0 < 1 && ((true >= false && \"abc\" < \"cba\") && \"A' == \"A\") {"
            //+ " println \"Ufa, deu certo!\";"
            //+ "}\r\n"
            ).toCharArray();


    Compiler compiler = new Compiler();
    Program program = compiler.compile(input);

    Map<String, Object> memory = new HashMap<>();

      program.genC(new PrintWriter(System.out));

      program.eval(memory);


  }


}
