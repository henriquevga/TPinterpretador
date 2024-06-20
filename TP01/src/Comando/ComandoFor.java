package Comando;
 import Variavel.*;
	import Expressao.*;
public class ComandoFor extends Comando implements Condicao{
    private int linha;
    private int linhaEnd;
    private Expressao raizArvoreExpressao;
    private double valor;
    private char variavel;
    private int incremento;

  public   ComandoFor(int lin,char c, double v, Expressao expo, int increment){
      raizArvoreExpressao=expo;
      incremento = increment;
      valor = v;
      variavel = c;
      linha = lin;
    }

    public void setLinhaEnd(int linhaF){
    this.linhaEnd=linhaF;
    }

    public int executa(){
      if(incremento ==-1)

        if(valor >= raizArvoreExpressao.avalia()){
          System.out.println(valor);
          Variaveis.var[variavel-97] = valor;
          valor = valor + incremento;
          return linha+1;
        }

      if(incremento ==1)

        if(valor <= raizArvoreExpressao.avalia()){
          System.out.println(valor);
          Variaveis.var[variavel-97] = valor;
          valor = valor + incremento;
          return linha+1;
        }

      return linhaEnd+1;
    }


}