package lexer;

public class Lexer {

  private char[] input;
  private int tokenPos;
  public Symbol token;
  private String stringValue;
  private String stringIdent;
  private int valueNumber;


  public Lexer(char []input) {
    this.input = input;
    input[input.length - 1] = '\0';
    tokenPos = 0;

  }

  public void nextToken(){
    char ch = input[tokenPos];;

    while((ch = input[tokenPos]) == ' ' || ch == '\r' ||
            ch == '\t' || ch == '\n') {
      tokenPos++;
    }

    if(input[tokenPos] == '\0'){
      token = Symbol.EOF;
    }else {
      //comentarios simples //
      if (ch == '/' && input[tokenPos + 1] == '/') {
        while (input[tokenPos] != '\0' && input[tokenPos] != '\n') {
          tokenPos++;
        }
        nextToken();
        //comentários em bloco /* */
      }else if (input[tokenPos] == '/' && input[tokenPos + 1] == '*') {
        tokenPos += 2;
        while (input[tokenPos] != '*' || input[tokenPos + 1] != '/') {
          tokenPos++;
        }
        tokenPos += 2;
        nextToken();
      } else if(Character.isLetter(ch)) {
        //var
        if( ch == 'v' && tokenPos + 2 < input.length && input[tokenPos+1] == 'a' && input[tokenPos+2] == 'r') {
          token = Symbol.VAR;
          tokenPos += 3;
        }
        //Int
        else if(ch == 'I' && tokenPos + 2 < input.length && input[tokenPos+1] == 'n' && input[tokenPos+2] == 't') {
          token = Symbol.Int;
          tokenPos += 3;
        }
        //if
        else if(ch == 'i' && tokenPos + 1 < input.length && input[tokenPos+1] == 'f'){
          token = Symbol.IF;
          tokenPos += 2;
        }
        //else
        else if(ch == 'e' && tokenPos + 3 < input.length && input[tokenPos+1] == 'l' && input[tokenPos+2] == 's'
                && input[tokenPos+3] == 'e'){
          token = Symbol.ELSE;
          tokenPos += 4;
        }
        //for
        else if(ch == 'f' && tokenPos + 2 < input.length && input[tokenPos+1] == 'o' && input[tokenPos+2] == 'r'){
          token = Symbol.FOR;
          tokenPos += 3;
        }
        //in
        else if(ch == 'i' && tokenPos + 1 < input.length && input[tokenPos+1] == 'n'){
          token = Symbol.IN;
          tokenPos += 2;
        }
        //println
        else if(ch == 'p' && tokenPos + 6 < input.length && input[tokenPos+1] == 'r' && input[tokenPos+2] == 'i' && input[tokenPos+3] == 'n'
                && input[tokenPos+4] == 't' && input[tokenPos+5] == 'l' && input[tokenPos+6] == 'n'){
          token = Symbol.PRINTLN;
          tokenPos += 7;
        }
        //print
        else if(ch == 'p' && tokenPos + 4 < input.length && input[tokenPos+1] == 'r' && input[tokenPos+2] == 'i' && input[tokenPos+3] == 'n'
                && input[tokenPos+4] == 't'){
          token = Symbol.PRINT;
          tokenPos += 5;
        }
        //while
        else if(ch == 'w' && tokenPos + 4 < input.length && input[tokenPos+1] == 'h' && input[tokenPos+2] == 'i'
                && input[tokenPos+3] == 'l' && input[tokenPos+4] == 'e'){
          token = Symbol.WHILE;
          tokenPos += 5;
        }
        //String
        else if(ch == 'S' && tokenPos + 5 < input.length && input[tokenPos+1] == 't' && input[tokenPos+2] == 'r' &&
                input[tokenPos+3] == 'i' && input[tokenPos+4] == 'n' && input[tokenPos+5] == 'g') {
          token = Symbol.String;
          tokenPos += 6;
        }
        //Boolean
        else if(ch == 'B' && tokenPos + 6 < input.length && input[tokenPos+1] == 'o' && input[tokenPos+2] == 'o' &&
                input[tokenPos+3] == 'l' && input[tokenPos+4] == 'e' && input[tokenPos+5] == 'a' && input[tokenPos+6] == 'n' ) {
          token = Symbol.Boolean;
          tokenPos += 7;
        }
        //true
        else if(ch == 't' && tokenPos + 3 < input.length && input[tokenPos+1] == 'r' && input[tokenPos+2] == 'u'
                && input[tokenPos+3] == 'e'){
          token = Symbol.TRUE;
          tokenPos += 4;
        }
        //false
        else if(ch == 'f' && tokenPos + 4 < input.length && input[tokenPos+1] == 'a' && input[tokenPos+2] == 'l'
                && input[tokenPos+3] == 's' && input[tokenPos+4] == 'e'){
          token = Symbol.FALSE;
          tokenPos += 5;
        }
        else {
          StringBuffer ident = new StringBuffer();

          while(Character.isLetterOrDigit(input[tokenPos])) {
            ident.append(input[tokenPos]);
            tokenPos++;
          }
          stringValue = ident.toString();
          token = Symbol.IDENT;
        }
      } else if(Character.isDigit(ch)){
        StringBuffer number = new StringBuffer();
        while ( Character.isDigit( input[tokenPos] ) ) {
          number.append(input[tokenPos]);
          tokenPos++;
        }
        valueNumber = Integer.valueOf(number.toString()).intValue();
        token = Symbol.NUMBER;
      }else {
        switch (ch) {
          case ';':
            token = Symbol.SEMICOLON;
            tokenPos++;
            break;
          case ')':
            token = Symbol.RIGHTPAR;
            tokenPos++;
            break;
          case '(':
            token = Symbol.LEFTPAR;
            tokenPos++;
            break;
          case '+':
            if(input[tokenPos+1] == '+'){
              token = Symbol.PLUSPLUS;
              tokenPos++;
            }else
              token = Symbol.PLUS;
            tokenPos++;
            break;
          case '-':
            token = Symbol.MINUS;
            tokenPos++;
            break;
          case '*':
            token = Symbol.MULT;
            tokenPos++;
            break;
          case '/':
            token = Symbol.DIV;
            tokenPos++;
            break;
          case '%':
            token = Symbol.REMAINDER;
            tokenPos++;
            break;
          /*==*/
          case '=':
            if(input[tokenPos+1] == '=') {
              token = Symbol.EQ;
              tokenPos++;
            }else
              token = Symbol.ASSIGN;
            tokenPos++;
            break;
          /*! ou !=*/
          case '!':
            if(input[tokenPos+1] == '=') {
              token = Symbol.NEQ;
              tokenPos++;
            }else
              token = Symbol.NOT;
            tokenPos++;
            break;
          case '{':
            token = Symbol.OPENCHAVE;
            tokenPos++;
            break;
          case '}':
            token = Symbol.CLOSECHAVE;
            tokenPos++;
            break;
          case '.':
            if(input[tokenPos+1] == '.'){
              token = Symbol.TWODOT;
              tokenPos+=2;
            }
            break;
          case '&':
            if(input[tokenPos+1] == '&'){
              token = Symbol.AND;
              tokenPos+=2;
            }
            break;
          case '|':
            if(input[tokenPos+1] == '|'){
              token = Symbol.OR;
              tokenPos+=2;
            }
            break;
          //< ou <=
          case '<':
            if(input[tokenPos+1] == '='){
              token = Symbol.LE;
              tokenPos++;
            }else
              token = Symbol.LT;
            tokenPos++;
            break;
          //> ou >=
          case '>':
            if(input[tokenPos+1] == '='){
              token = Symbol.GE;
              tokenPos++;
            }else
              token = Symbol.GT;
            tokenPos++;
            break;
          //TODO - trocar para aspas duplas '\"'
          case '\'':
            StringBuffer ident = new StringBuffer();
            token = Symbol.LiteralString;
            tokenPos++;
            while(input[tokenPos] != '\''){
              ident.append(input[tokenPos]);
              tokenPos++;
            }
            stringIdent = ident.toString();
            tokenPos++;
            break;
          default:
            System.out.println("Caracter invalido: " + ch);
        }
      }
    }
  }

  public String getStringValue() {
    return stringValue;
  }

  public String getStringIdent() {
    return stringIdent;
  }

  public int getValueNumber() {
    return valueNumber;
  }
}
