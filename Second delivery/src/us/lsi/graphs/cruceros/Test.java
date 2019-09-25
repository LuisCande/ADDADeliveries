package us.lsi.graphs.cruceros;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;

public class Test {

	public static void main(String[] args) {

		// TEST METODO 2

		MapaDeNavegacion mn = MapaDeNavegacion.read("ficheros//cruceros.txt");
		Graph<Puerto, Trayecto> g = mn.getMapa();
		System.out.println("Test Metodo 2)" + g);

		// TEST METODO 3

		System.out.println("Test Metodo 3)" + mn.getPuertoById("Alesund"));

		
		// TEST METODO 7

		List<Puerto> travesia = new ArrayList<Puerto>();
		travesia.add(mn.getPuertoById("Barcelona"));
		travesia.add(mn.getPuertoById("Napoles"));
		travesia.add(mn.getPuertoById("Santorini"));
		travesia.add(mn.getPuertoById("Rodas"));
		System.out.println("Test Metodo 7)" + mn.getDuracionDeCrucero(travesia));
	
		// TEST METODO 8

		List<Puerto> travesia8 = new ArrayList<Puerto>();
		travesia8.add(mn.getPuertoById("Barcelona"));
		travesia8.add(mn.getPuertoById("Napoles"));
		travesia8.add(mn.getPuertoById("Santorini"));
		travesia8.add(mn.getPuertoById("Rodas"));
		System.out.println("Test Metodo 8)" + mn.getAtractivoMedio(travesia8));
		
		// TEST METODO 9

		List<Puerto> travesia2 = new ArrayList<Puerto>();
		travesia2.add(mn.getPuertoById("Estocolmo"));
		travesia2.add(mn.getPuertoById("Napoles"));
		travesia2.add(mn.getPuertoById("Santorini"));
		travesia2.add(mn.getPuertoById("Rodas"));
		System.out.println("Test Metodo 9)" + mn.existeAlgunPuntoAbastecimiento(travesia2));

		// TEST METODO 10

		List<Puerto> travesia1 = new ArrayList<Puerto>();
		travesia1.add(mn.getPuertoById("Barcelona"));
		travesia1.add(mn.getPuertoById("Napoles"));
		travesia1.add(mn.getPuertoById("Santorini"));
		travesia1.add(mn.getPuertoById("El Pireo"));
		System.out.println("Test Metodo 10)" + mn.getCosteXCrucero(travesia1));


		// TEST METODO 12

		System.out.println("Test Metodo 12)" + mn.getCrucerosValidos().size());

	}

}
