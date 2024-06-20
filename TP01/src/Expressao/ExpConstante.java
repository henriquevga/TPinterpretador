package Expressao;

public class ExpConstante extends Expressao{
  private double valor;
 public ExpConstante(double valore){
    this.valor=valore;
  }
  public double avalia(){
    return valor;
  }
}