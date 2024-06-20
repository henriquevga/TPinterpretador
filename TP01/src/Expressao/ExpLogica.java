package Expressao;

import Variavel.*;

public class ExpLogica extends Expressao{
Expressao expo1;
Expressao expo2;
String operador;

  public ExpLogica(String op,Expressao exp1,Expressao exp2){
    this.expo1=exp1;
    this.expo2=exp2;
    this.operador=op;
  }

  public double avalia(){
    boolean cmd1=false;
    boolean cmd2=false;

    if(expo1.avalia()==1){
      cmd1=true;
    }
    if(expo2.avalia()==1){
      cmd2=true;
    }


    if(operador.equals("and")){
      if(cmd1 && cmd2){
        return 1;
      }
    }
    else if(operador.equals("or")){
      if(cmd1 || cmd2){
        return 1;
      }
    }
    else if(operador.equals("not")){
      if(!cmd2 || !cmd1){
        return 1;
      }
    }
    return 0;

  }


}