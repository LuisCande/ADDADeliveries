package ti4.jarras.pd;

import ti4.jarras.*;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;

public class TestJarraPD {
	public static void main(String[] args) {
		
		AlgoritmoPD.isRandomize = false;
		ProblemaJarra.create("ficheros/Jarras.txt", "ficheros/Operaciones.txt");
		ProblemaJarra.cantidadFinalEnJarra1 = 2;
		ProblemaJarra.cantidadFinalEnJarra2 = 0;
		ProblemaJarra.numMaxOperaciones = 10;
		System.out.println("------");
		
		System.out.println(ProblemaJarra.jarra1);
		System.out.println(ProblemaJarra.jarra2);
		System.out.println(ProblemaJarra.operaciones);
		
		
		ProblemaJarraPD p = ProblemaJarraPD.create();
		AlgoritmoPD<SolucionJarra, Accion> a = Algoritmos.createPD(p);
		a.ejecuta();
		if (a.solucionesParciales.get(p) != null){
			System.out.println("NumOps: " + a.solucionesParciales.get(p).propiedad);
			System.out.println("Solucion: " + a.getSolucion(p));
			a.showAllGraph("ficheros/solucion.gv", "Solucion", p);
		}else{
			System.out.println("Solución no encontrada");
		}
		
	}

}
