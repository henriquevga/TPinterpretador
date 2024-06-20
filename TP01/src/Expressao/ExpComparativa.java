package Expressao;

import Expressao.Expressao;


public class ExpComparativa extends Expressao{
Expressao expo1;
Expressao expo2;
String operador;

 public ExpComparativa(String op,Expressao exp1,Expressao exp2){
    this.expo1=exp1;
    this.expo2=exp2;
    this.operador=op;
  }

  public double avalia(){
    if(operador.equals(">")){
      if(expo2.avalia()>expo1.avalia())
      //System.out.println(expo1.avalia()+operador+expo2.avalia());
        return 1;
    }
    else if(operador.equals("<")){
      //System.out.println(expo1.avalia()+operador+expo2.avalia());
      if(expo2.avalia()<expo1.avalia()){
        return 1;
      }
    }

    else if(operador.equals("<=")){
      if(expo2.avalia()<=expo1.avalia()){
        return 1;
      }
    }

    else if(operador.equals(">=")){
      if(expo2.avalia()>=expo1.avalia()){
        return 1;
      }
    }

    else if(operador.equals("=")){
      if(expo2.avalia()==expo1.avalia()){
        return 1;
      }

    }
    else if(operador.equals("<>")){
      if(expo2.avalia()!=expo1.avalia()){
        return 1;
      }
    }
    else if(true){
    return 0;
  }
  return 31;
  }

}