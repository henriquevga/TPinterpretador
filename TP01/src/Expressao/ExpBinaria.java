package Expressao;

import Variavel.*;


public class ExpBinaria extends Expressao{
  String operador;
  Expressao expo1;

  Expressao expo2;
 public ExpBinaria(String op,Expressao exp1, Expressao exp2){
    this.expo1=exp1;
    this.expo2=exp2;
    this.operador=op;
  }

  public double avalia(){
    Expressao cmd1 =expo1;
    Expressao cmd2 =expo2;
    double valor =0;
    if(operador.charAt(0)=='+'){
      //System.out.println("\n+="+cmd1.avalia()+cmd2.avalia()+"\n");
      valor= cmd1.avalia()+cmd2.avalia();
    }
    else if(operador.charAt(0)=='-'){
      //System.out.println("\n-="+cmd1.avalia()+cmd2.avalia()+"\n");
      valor= cmd2.avalia()-cmd1.avalia();
    }
    else if(operador.charAt(0)=='*'){
      valor= cmd1.avalia()*cmd2.avalia();
    }
    else if(operador.charAt(0)=='/'){
      valor= cmd2.avalia()/cmd1.avalia();
    }
    return valor;
  }
}