   package Comando;
	
   import Variavel.*;
	 
   public class ComandoElse extends Comando implements Condicao {

   int linhaEnd;
	 int linha;

   public ComandoElse(int lin) {
		this.linha=lin;
   }

   public void setLinhaEnd(int x){
		 linhaEnd=x;
   }
   public int executa() {
     if(linhaEnd!=linha){
       return linhaEnd;
     }
     else
		 return linha+1;
   }
}

