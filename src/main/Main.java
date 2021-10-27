package main;

/*TODO

  - Ajustar aspas duplas inves de aspa simples

  - Criar eval
    - print e println podem imprimir valores de todos os tipos. Para true booleano, deve ser impresso true mesmo. Idem para false.
    - como retornar uma string, boolean no print


 */


import ast.Program;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String []args){

    /*char []input = (" var Int n;"
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
            + "     println 'O valor dessa porra é = ' ++ (souVerd ++ ' ') ++ cont;"
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


    File file;
    FileReader stream;

    file = new File(args[1]);
    if ( ! file.exists() || ! file.canRead() ) {
      System.out.println("Arquivo " + args[1] + " não existe ou não pode ser lido");
      return ;
    }
    try {
      stream = new FileReader(file);
    } catch ( FileNotFoundException e ) {
      System.out.println("Erro: arquivo não existe mais");
      throw new RuntimeException();
    }
    char []input = new char[ (int ) file.length() + 1 ];

    try {
      stream.read( input, 0, (int ) file.length() );
    } catch ( IOException e ) {
      System.out.println("Erro ao ler o arquivo " + args[1]);
      return ;
    }

    Compiler compiler = new Compiler();
    Program program = compiler.compile(input);

    Map<String, Integer> memory = new HashMap<>();

    if(args[0].equals("-gen")){
      program.genC(new PrintWriter(System.out));

    }else if(args[0].equals("-run")){
      program.eval(memory);
    }

  }


}
