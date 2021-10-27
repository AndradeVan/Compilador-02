package main;

/*TODO

  - Ajustar aspas duplas inves de aspa simples

  - Criar eval
    - print e println podem imprimir valores de todos os tipos. Para true booleano, deve ser impresso true mesmo. Idem para false.
    - como retornar uma strinf, boolean no print
    - for ok
    - print e println ok

  - Verificar escopo das variaveis -> não precisa, mas podemos fazer

  - Criar class de erro

 */


import ast.Program;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String []args){
    char []input = ("var Int n;"
            + "n = 100;"
            + "var Int b;"
            + "b = 2;"
            + "var Boolean i;"
            + "i = true;"
            + "print i;"
            + "println n;\r\n"
            + "for j in 1..10 {"
            + "print j;"
            + "}\r\n"
            + "if i {"
            + "println false;"
            + "}else{"
            + " print b;"
            + "}\r\n"
            + "var Boolean souVerd;"
            +"for cont in 1..5 {"
                +"souVerd = true;"
                + "println (souVerd ++ ' ') ++ cont;"
            +"}\r\n"
    ).toCharArray();
    /*char []input = (" var Int n;"
            + "n = 100;"
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
            + "println 'soma = ' ++ soma;"
            + "println 'somaFor = ' ++ somaFor;"
            + "var Int num;"
            + "var String str;"
            + "var Boolean souVerd;"
            + "if soma == somaFor {"
            + " num = 0;"
            + " if num > 0 {"
            + "   str = 'sou String';"
            + "   for cont in 1..5 {"
            + "     souVerd = true;"
            + "     println (souVerd ++ ' ') ++ cont;"
            + "   }"
            + "   str = 'continuo String';"
            + "   println 'str = ' ++ str;"
            + " }"
            + " else {"
            + "   num = 1;"
            + "   println 'num = ' ++ num;"
            + " }"
            + " println 'true = ' ++ (true >= false);"
            + "}"
            + "else {"
            + " println 'Alguma coisa errada! Soma != SomaFor';"
            + "}"
            + "if 0 < 1 && ((true >= false && 'abc' < 'cba') && 'A' == 'A') {"
            + " println 'Ufa, deu certo!';"
            + "}\r\n"
            ).toCharArray();*/


    Compiler compiler = new Compiler();
    Map<String, Integer> memory = new HashMap<>();
    Program program = compiler.compile(input);

    program.genC(new PrintWriter(System.out));
//    program.eval(memory);
  }


}
