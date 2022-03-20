import HelloApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;

public class HelloClient
{
  static Hello helloImpl;

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.

        System.out.println("Dime una frase:");
        Scanner escribir = new Scanner (System.in);
        String entrada = escribir.nextLine();

        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        // resolve the Object Reference in Naming
        helloImpl = HelloHelper.narrow(ncRef.resolve_str(entrada));

        System.out.println("El numero de palabras de tu frase es:"+ helloImpl.ContarPalabras(entrada));
        System.out.println();
        System.out.println("¿Que quieres hacer poner la frase en mayusculas o en minusculas?(mayus | minus)");

        Scanner a= new Scanner (System.in);
        String eleccion = a.nextLine();
         if(eleccion=="mayus"||eleccion=="Mayus"){
           System.out.println("Tu frase en mayusculas es: " + helloImpl.Mayus(entrada));
         }else if(eleccion=="minus"||eleccion=="Mayus"){
           System.out.println("Tu frase en minusculas es: " + helloImpl.Minus(entrada));
         }else{
           System.out.println("Escribe una opcion valida");
         }
        
        System.out.println("La palabra mas larga de tu frase es:"+ helloImpl.PalabraLarga(entrada));


       

        helloImpl.shutdown();

        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}