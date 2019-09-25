package ti3.vagones.pl;

import ti3.vagones.ProblemaVagones;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pl.AlgoritmoPLI;

public class TestVagonesPLI {
	
	public static void main(String[] args) {
		ProblemaVagones.create("Vagones.txt");
		ProblemaVagones.pesoTotal = 20;
		System.out.println("------");
		System.out.println("Peso total: " + ProblemaVagones.pesoTotal);
		System.out.println("------");
		System.out.println("Vagones:\n" + ProblemaVagones.listaVagones);
		System.out.println("------");
		
		String r = VagonesPLI.getConstraints();
		// TODO
		AlgoritmoPLI a = Algoritmos.createPLI("ficheros/restricciones.txt");
		a.setConstraints(r);
		a.ejecuta();
		System.out.println("Especificación LPSolve:");
		System.out.println(r);
		System.out.println("Objetivo Solución Óptima = " +a.getObjetivo());
		System.out.println("Solución Óptima = ");
		for(int i=0; i<=a.getSolucion().length-1; i++){
			System.out.println("x" + i + " = " + a.getSolucion(i));
			}
	}

}
