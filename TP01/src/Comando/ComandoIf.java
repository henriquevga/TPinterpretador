   package Comando;
	
   import Variavel.*;
	import Expressao.*;
	 
public class ComandoIf extends Comando implements Condicao{
  int linhaEnd;
  int linha;
  Expressao raizArvoreExpressao;
    public ComandoIf(int linha,Expressao raizArvoreExpressao){
    this.linha=linha;
    this.raizArvoreExpressao=raizArvoreExpressao;
  }
  public int executa(){
    if(raizArvoreExpressao.avalia()==1)
    return linha+1;
    else
    return linhaEnd;
  }
  public void setLinhaEnd(int lin){
    linhaEnd=lin;
  }



}

