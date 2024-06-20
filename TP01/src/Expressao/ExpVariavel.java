   package Expressao;

   import Variavel.*;


public class ExpVariavel extends Expressao{
  private char nomevar;
 public  ExpVariavel(char nome){
    this.nomevar=nome;
  }
  public double avalia(){
    return Variaveis.var[nomevar - 97];
  }

}


