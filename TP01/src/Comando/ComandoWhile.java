   package Comando;
	
   import Variavel.*;
	import Expressao.*;
	 
public class ComandoWhile extends Comando implements Condicao{
    private int linha;
    private int linhaEnd;
    private Expressao raizArvoreExpressao;
    public ComandoWhile(int lin, Expressao expo){
      linha=lin;
      this.raizArvoreExpressao=expo;
    }
    public void setLinhaEnd(int linha1){
    this.linhaEnd=linha1+1;
    }
    public int executa(){
      if(raizArvoreExpressao.avalia()==1){
        return linha+1;
      }

      return linhaEnd;
    }

}

