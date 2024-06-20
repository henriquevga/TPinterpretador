   package Comando;
	
   import Variavel.*;
	import Expressao.*;
	 
    public class ComandoAtrib extends Comando{
    int lin;
    char nome;
    Expressao raizArvoreExpressao;
    public ComandoAtrib(int linha,char var,Expressao raizArvoreExpressao){
      this.lin=linha;
      this.nome=var;
      this.raizArvoreExpressao=raizArvoreExpressao;
    }
    public int executa(){
      Variaveis.var[nome-97]=raizArvoreExpressao.avalia();
      return lin+1;
    }

}

