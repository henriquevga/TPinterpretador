   package Comando;
	
   import Variavel.*;
	import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;	 
public class ComandoRead extends Comando {

	BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
	char variavel;

	public ComandoRead(int lin, String txt) {
		linha = lin;
		variavel = txt.charAt(0);
	}

	public int executa() {
		char posicao;
		try {
			String valor = teclado.readLine();
			Variaveis.var[variavel-97] = Double.parseDouble(valor);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
		}
		return linha + 1;
	}
}
