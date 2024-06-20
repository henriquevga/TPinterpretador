   package Comando;
 
import Variavel.*;
 
public class ComandoEndfor extends Comando{
  int linhafor;

  public ComandoEndfor(int linhaf) {
   this.linhafor=linhafor+1;
  }
  public int executa() {
      return linhafor+1;
    }
}