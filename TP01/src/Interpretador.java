   import Variavel.*;
   import Comando.*;
   import Expressao.*;
   import java.util.*;
   import lp.*;


import java.util.Vector;
import java.util.Stack;

import lp.*;

class Interpretador {
	private Stack pilha;
	private ArquivoFonte arq;
	private Vector comandos;
	private String palavraAtual;
	private Expressao raizArvoreExpressao;

	public Interpretador(String nome) {
		arq = new ArquivoFonte(nome);
		comandos = new Vector();
	}

private void expLogica(){
	expComparativa();
	while ((palavraAtual.equals("and"))  || (palavraAtual.equals("or") ) || (palavraAtual.equals("not"))) {
		String op = palavraAtual;
		palavraAtual= arq.proximaPalavra();
		expComparativa();
		Expressao exp1= (Expressao)pilha.pop();
		Expressao exp2= (Expressao)pilha.pop();
		pilha.push(new ExpLogica(op,exp1,exp2));
	}

}

private void expComparativa(){
	expressao();
	while ((palavraAtual.equals(">"))  || (palavraAtual.equals("<")) || (palavraAtual.equals(">=")) ||
	 					(palavraAtual.equals("<=")) || (palavraAtual.equals("=")) || (palavraAtual.equals("<>"))) {
		String op = palavraAtual;
		palavraAtual= arq.proximaPalavra();
		expressao();
		Expressao exp1= (Expressao)pilha.pop();
		Expressao exp2= (Expressao)pilha.pop();
		pilha.push(new ExpComparativa(op,exp1,exp2));
	}

}

 private void expressao() {
		termo();
		while ((palavraAtual.equals("+"))  || (palavraAtual.equals("-") )) {
			String op = palavraAtual;
			palavraAtual= arq.proximaPalavra();
			termo();
			Expressao exp1= (Expressao)pilha.pop();
			if(pilha.empty()){
				pilha.push(new ExpConstante(0.0));
			}
			Expressao exp2= (Expressao)pilha.pop();
			pilha.push(new ExpBinaria(op,exp1,exp2));
		}
 }
 private void termo() {
		fator();
		while ((palavraAtual.equals("*")) || (palavraAtual.equals("/") )) {
			String op= palavraAtual;
			palavraAtual= arq.proximaPalavra();
			fator();
			Expressao exp1= (Expressao)pilha.pop();
			Expressao exp2= (Expressao)pilha.pop();
			pilha.push(new ExpBinaria(op,exp1,exp2));
		}
 }

 private void fator() {
         if ( palavraAtual.equals("sqrt") ) {
            palavraAtual= arq.proximaPalavra();
            palavraAtual= arq.proximaPalavra();
            pilha.push(new ExpSqrt( palavraAtual ));
            palavraAtual= arq.proximaPalavra();
            palavraAtual= arq.proximaPalavra();
         }

         else if ( palavraAtual.charAt(0) >= '0' && palavraAtual.charAt(0) <= '9'  ) {
            pilha.push(new ExpConstante( Double.parseDouble(palavraAtual) ));
            palavraAtual= arq.proximaPalavra();
         }

         else if ( palavraAtual.charAt(0) >= 'a' && palavraAtual.charAt(0) <= 'z'  ) {
            pilha.push(new ExpVariavel( palavraAtual.charAt(0) ));
            palavraAtual= arq.proximaPalavra();
         }

         else if ( palavraAtual.equals("(") ) {
            palavraAtual= arq.proximaPalavra();
            expLogica();

            if ( palavraAtual.equals(")") ) {
               palavraAtual= arq.proximaPalavra();
            }

         }

      }



	public void listaArquivo() {
		String palavra;

		do {
			palavra = arq.proximaPalavra();
		} while (!palavra.equals("EOF"));
	}

	public void leArquivo() {
		String comandoAtual;
		Stack pilhaC= new Stack();
		int linha = 0;
		pilhaC.push(linha);
		do {
			comandoAtual = arq.proximaPalavra();

			if (comandoAtual.equals("endp")) {
				trataComandoEndp(linha);
				linha+=1;
			} else if (comandoAtual.equals("writeln")) {
				trataComandoWriteln(linha);
				linha+=1;
			}
			else if(comandoAtual.equals("writeStr")){
				arq.proximaPalavra();
				trataComandoWriteStr(linha,arq.proximaPalavra());
				linha+=1;
			}
			else if(comandoAtual.equals("read")){
				arq.proximaPalavra();
				trataComandoRead(linha,arq.proximaPalavra());
				linha+=1;
			}
			else if(comandoAtual.equals("writeVar")){
				arq.proximaPalavra();
				trataComandoWriteVar(linha,arq.proximaPalavra());
				linha+=1;

			}
			else if(comandoAtual.equals("if")){
        pilhaC.push(linha);
				trataComandoIf(linha);
        linha+=1;
      }
			else if(comandoAtual.equals("else")){
        int linhaIf = (Integer)pilhaC.pop();
				pilhaC.push(linha);
				trataComandoElse(linha, linhaIf);
        linha+=1;
      }
			else if(comandoAtual.equals("endif")){
        int linhaIf = (Integer)pilhaC.pop();
				trataComandoEndif(linha, linhaIf);
      }
			else if(comandoAtual.equals("while")){
      	pilhaC.push(linha);
				trataComandoWhile(linha);
      	linha+=1;
      }
			else if(comandoAtual.equals("endw")){
        int linhaW = (Integer)pilhaC.pop();
				trataComandoEndw(linha, linhaW);
				linha+=1;
      }
			else if(comandoAtual.equals("for")){
				int incremento=0;
				char variavel =  arq.proximaPalavra().charAt(0); // Variavel a ser incrementada
				arq.proximaPalavra();
				Variaveis.var[variavel - 97] = Double.valueOf(arq.proximaPalavra()); // Declara valor inicial

				String down_or_to = arq.proximaPalavra();
					if(down_or_to.equals("downto")){
						incremento = -1;
					}
					else if(down_or_to.equals("to")){
						incremento = 1;
					}
					pilhaC.push(linha);
					trataComandoFor(linha,variavel,Variaveis.var[variavel-97],incremento);
					linha+=1;
      }

			else if(comandoAtual.equals("endfor")){
        int linhaF = (Integer)pilhaC.pop();
				trataComandoEndfor(linha,linhaF);
				linha+=1;
      }
			else if(97<=comandoAtual.charAt(0) && 122>=comandoAtual.charAt(0)){
				arq.proximaPalavra();
				trataComandoAtribuicao(linha,comandoAtual);
				linha+=1;
			}

		} while (!comandoAtual.equals("endp"));
	}
	private void trataExpressao() {
	 palavraAtual= arq.proximaPalavra();
	 pilha= new Stack();
	 expLogica();
	 raizArvoreExpressao= (Expressao) pilha.pop();
 	}
	private void trataComandoIf(int lin){
		trataExpressao();
		ComandoIf c = new ComandoIf(lin,raizArvoreExpressao);
		comandos.addElement(c);
	}
	private void trataComandoElse(int lin, int linif){
		ComandoIf cmd= (ComandoIf) comandos.elementAt(linif);
		cmd.setLinhaEnd(lin+1);
		ComandoElse c= new ComandoElse(lin);
		comandos.addElement(c);
	}
	private void trataComandoEndif(int lin, int linIfElse) {
		Condicao cmd= (Condicao) comandos.elementAt(linIfElse);
		cmd.setLinhaEnd(lin);
	}
	private void trataComandoWhile(int lin){
		trataExpressao();
		ComandoWhile c = new ComandoWhile(lin,raizArvoreExpressao);
		comandos.addElement(c);
	}
	private void trataComandoEndw(int lin,int linhaw){
		Condicao cmd = (ComandoWhile) comandos.elementAt(linhaw);
		cmd.setLinhaEnd(lin);
		ComandoEndw c = new ComandoEndw(linhaw);
		comandos.addElement(c);

	}
	private void trataComandoFor(int lin,char variavel,double valor,int incremento){
		trataExpressao();
		ComandoFor c = new ComandoFor(lin,variavel,valor,raizArvoreExpressao,incremento);
		comandos.addElement(c);

	}
	private void trataComandoEndfor(int lin,int linhaF){
		Condicao cmd = (ComandoFor) comandos.elementAt(linhaF);
		cmd.setLinhaEnd(lin);
		ComandoEndfor c = new ComandoEndfor(linhaF);
		comandos.addElement(c);

	}


	private void trataComandoAtribuicao(int lin, String var){
		char variavel = var.charAt(0);
		trataExpressao();
		ComandoAtrib c = new ComandoAtrib(lin,variavel,raizArvoreExpressao);
		comandos.addElement(c);
	}

	private void trataComandoEndp(int lin) {

		ComandoEndp c = new ComandoEndp(lin);
		comandos.addElement(c);
	}

	private void trataComandoWriteln(int lin) {

		ComandoWriteln c = new ComandoWriteln(lin);
		comandos.addElement(c);
	}
	private void trataComandoWriteStr(int lin,String texto){

		ComandoWriteStr c = new ComandoWriteStr(lin,texto);
		comandos.addElement(c);
	}
	private void trataComandoRead(int lin,String texto){
		ComandoRead c = new ComandoRead(lin,texto);
		comandos.addElement(c);
	}
	private void trataComandoWriteVar(int lin,String texto){
		ComandoWriteVar c = new ComandoWriteVar(lin,texto);
		comandos.addElement(c);
	}


	public void executa() {

		Comando cmd;
		int pc = 0;
		do {
			cmd = (Comando) comandos.elementAt(pc);
			pc = cmd.executa();


		} while (pc != -1);
	}
}