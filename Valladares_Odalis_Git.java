//TAREA ENTREGABLE 3
import java.util.Scanner;
public class Valladares_Odalis_Git {
   final static int ANNO_INICIAL=20;
   final static int ANNO_FINAL=25;
   final static int INT_INICIAL=3;
   final static int INT_FINAL=5;
   public static void main (String [] args){
   //Creacion del objeto Scanner.
      Scanner scanner = new Scanner(System.in);
   // Metodo para primera parte introduccion y solicituda de la cantidad de dinero. 
      int prestamo = introduccion(scanner);
   //Metodo para el desglose de intereses por año.
      calculo(prestamo);
      //desglose capital pendiente, cuota anual, intereses a pagar y amortizacion por año.
      Parte2(scanner, prestamo);
      scanner.close();
   }
   //Metodo para redondear todo lo que se mostrará en pantalla
   public static double Redondeo(double numero) {
      double Red=(double) Math.round(numero *100)/100;
      return Red;
   }
   //metodo calculo de la cuotaAnual para utilizar en dos metodos distintos
   public static double CuotaAnual (double cantidadCapital, double interes, double annos) {
      double cuotaAnual = (cantidadCapital *  (interes/100) / (1 - Math.pow(1 + interes/100 , - annos)));
      return cuotaAnual;
   }
   //Desarrollo del metodo que contiene la introduccion.
   public static int introduccion(Scanner scanner){
      System.out.println("Este es un simulador de hipoteca");
      System.out.println("PRIMERO");
      System.out.println("Solicita el monto del prestamo (euros) e imprime una tabla con diferentes opciones segun el interes y plazo");
      System.out.println("SEGUNDO");
      System.out.println("Solicita el monto de prestamo (euros), la tasa de interes anual a pagar (%) y el plazo (annos)");
      System.out.println("Calcula para cada anno, el capital pendiente y la cuota a pagar, intereses y amortizacion");
      System.out.println();
      System.out.print("Introduce la cantidad solicitada para el prestamo: ");
      int cantidad = scanner.nextInt();
      for (i=0; i<=3; i++) {
         System.out.print("Introduce la cantidad solicitada para el prestamo: ");
         int cantidad = scanner.nextInt();
         if (cantidad<0){
         System.out.print("Introduce la cantidad solicitada para el prestamo: ");
         int cantidad = scanner.nextInt();
         }
      }
      System.out.println();
      System.out.println("PRIMERO");
      System.out.println("Estas son las cuotas a pagar para diferentes intereses y plazos.");
      return cantidad;
   }
    //Desarrollo del metodo que contiene el bucle anidado, se le envia el parametro que ingreso el usuario en el metodo anterior
   public static void calculo(int cantidad) {
      //annoini es el numero de años a partir del que se harán los calculos
      for(int annoini=ANNO_INICIAL; annoini<=ANNO_FINAL; annoini++) {
         System.out.print(annoini + " annios ");
         for(double interes= INT_INICIAL; interes<= INT_FINAL; interes= interes+ 0.5){
            //llama al metodo Cuota anual con los parametros de la cantidad pedida al usuario, la tasa de interes y la cantidad de años que el usuario ingreso.
            double cuotaAnual=CuotaAnual(cantidad, interes, annoini);
            double cAnualRed = Redondeo(cuotaAnual);
            System.out.print(cAnualRed + "(" + interes + " %)\t");
         }
         System.out.println();           
      }
   }
   public static void Parte2(Scanner scanner, double prestamo){
      System.out.println();
      System.out.println("SEGUNDO");
      System.out.print("Introduce Introduce el interes anual que se aplicara al prestamo en %: ");
      double interes = scanner.nextDouble();
      System.out.print("Introduce el numero de annos que va a durar el prestamo: ");
      int annosprestamo = scanner.nextInt();
      //definicion de variables fuera del bucle, la cuota anual no cambiará.
      double CPnuevo=0;
      double capitalPendiente=prestamo;
      //calculo de la cuotaAnual por medio del metodo CuotaAnual con los parametros de capitalPendiente, interes y annos.
      double cuotaAnual=CuotaAnual(capitalPendiente, interes, annosprestamo);
      double cAnualRed=Redondeo(cuotaAnual);
      for(int annoini=1; annoini<=annosprestamo; annoini++) {
         System.out.println("Anno: " + annoini); 
         //intereses a pagar y su redondeo    
         double intAPagar=capitalPendiente*(interes/100);
         double intAPagarRed=Redondeo(intAPagar); 
         //Amortizacion y su redondeo
         double amort=cuotaAnual-intAPagar;
         double amortRed=Redondeo(amort);
         //actualizacion redondeado de capital pendiente
         double capitalPendienteRed=Redondeo(capitalPendiente);
         CPnuevo=capitalPendiente-amort;
         //aparecerá en pantalla los calculos ya redondeados
         System.out.println("Capital Pendiente: "+ capitalPendienteRed);
         System.out.println("Cuota Anual: " + cAnualRed);
         System.out.println("Intereses a pagar: "+ intAPagarRed);
         System.out.print("Amortizacion: "+amortRed);
         //capital pendiente fue inicializado como prestamo, y se le ira restando la amortizacion en cada vuelta
         capitalPendiente=capitalPendiente-amort;
         System.out.println();
      }
   }
}