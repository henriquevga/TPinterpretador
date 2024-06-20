   package Comando;
 
public class ComandoEndw extends Comando{
   private int linhawhile;

  public ComandoEndw(int linhaw) {
   this.linhawhile=linhaw;
  }
  public int executa() {
      return linhawhile;
    }
}