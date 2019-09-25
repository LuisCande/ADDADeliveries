package ti4.jarras.bt;

import ti4.jarras.*;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestJarraBT {
public static void main(String[] args) {
		
		ProblemaJarra.create("ficheros/Jarras.txt", "ficheros/Operaciones.txt");
		ProblemaJarra.cantidadFinalEnJarra1 = 2;
		ProblemaJarra.cantidadFinalEnJarra2 = 0;
		ProblemaJarra.numMaxOperaciones = 10;
		System.out.println("------");
		
		System.out.println(ProblemaJarra.jarra1);
		System.out.println(ProblemaJarra.jarra2);
		System.out.println(ProblemaJarra.operaciones);
		
		AlgoritmoBT.soloLaPrimeraSolucion = false;
		AlgoritmoBT.isRandomize = false;
		AlgoritmoBT.numeroDeSoluciones = 100;
		ProblemaJarraBT p = ProblemaJarraBT.create();
		AlgoritmoBT<SolucionJarra, Accion> a = Algoritmos.createBT(p);
		a.ejecuta();

		if (a.soluciones.isEmpty()) 
			System.out.println("Solución no encontrada");
		else{
			System.out.println("Solución: " + a.solucion);
		}
		
	}
}
