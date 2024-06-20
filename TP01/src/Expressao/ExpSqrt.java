package Expressao;

import Variavel.Variaveis;


public class ExpSqrt extends Expressao{

      String sqrt;

       public ExpSqrt(String sqrt) {
         this.sqrt= sqrt;
      }

       public double avalia() {
         double valor;
         if( sqrt.charAt(0) >= 'a' && sqrt.charAt(0)<= 'z' ){
            valor= Math.sqrt( Variaveis.var[ sqrt.charAt(0) - 97 ]);
         }
         else{
            valor= Math.sqrt(Double.parseDouble(sqrt));
         }

         return valor;
      }

   }
