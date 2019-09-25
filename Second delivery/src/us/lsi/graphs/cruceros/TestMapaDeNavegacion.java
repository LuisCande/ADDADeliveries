package us.lsi.graphs.cruceros;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;

import us.lsi.graphs.GraphsFileExporter;

import com.google.common.collect.Sets;

public class TestMapaDeNavegacion {
	static public void main(String[] args) {
		MapaDeNavegacion mn =  MapaDeNavegacion.read("ficheros//cruceros.txt");
		Graph<Puerto,Trayecto> g = mn.getMapa();
		
		Set<Puerto> ms = Sets.newHashSet();
		for (Puerto p:g.vertexSet()) {
			
		}
        for (Trayecto e:g.edgeSet()) {
			
			
		}
        
        GraphsFileExporter.<Puerto,Trayecto>saveFile(g, "ficheros//salida6.gsv", ms,new HashSet<Trayecto>());
        /*
		GraphsFileExporter.<Puerto,Trayecto>saveFile(g, "ficheros//salida6.gsv", 
				new IdPuertos(),
				new NamePuertos(),
				new NameTrayectos(),
				new EtiquetasPuertos(), 
				new EtiquetasTrayectos());
	     */
		
		Set<Puerto> s = mn.getRedDeControles();
		System.out.println("RED DE CONTROLES: " + s +  ". SIZE: " + s.size());
		System.out.println();
	
		int cont = 0;
		System.out.println("ZONAS DE INFLUENCIA(1): ");
		for (Set<Puerto> ss: mn.getZonasDeInfluencia(1)) {
			System.out.println((cont++) + " -> " + ss);
		}
		System.out.println();
		
		System.out.println("RED DE FERRIS:" + mn.getRedDeFerris());
		System.out.println();
		
		System.out.println("PUERTOS CON AEROPUERTOS CERCANOS: " + mn.getPuertosConAeropuertosCercanos());
		System.out.println();
		
		System.out.println("CRUCEROS DE FANTASTICO");
		System.out.println("======================");
		for (List<Puerto> c:mn.getCrucerosFantasticos()) {
			System.out.println(c + " -> " + 
		          "(a=" + mn.getAtractivoMedio(c) + ", " +
				   "d=" + mn.getDuracionDeCrucero(c) + ", " + 
		           "l=" + mn.existeAlgunPuntoAbastecimiento(c) + ", " + 
		           "c=" + mn.getCosteXCrucero(c) +
		        ")") ;
		}
		System.out.println();
		
	}
	
	private static class IdPuertos implements VertexNameProvider<Puerto> {

		@Override
		public String getVertexName(Puerto arg0) {
			// TODO Auto-generated method stub
			return "\"" + arg0.getId() + "\"";
		}
		
	}
	
	private static class NamePuertos implements VertexNameProvider<Puerto> {

		@Override
		public String getVertexName(Puerto v) {
			// TODO Auto-generated method stub
			//return arg0.getId();
			
			Map<String, String> map = new HashMap<>();
			map.put("t", String.valueOf(v.getTasas()));
			map.put("a", String.valueOf(v.getAtractivo()));
			map.put("c", String.valueOf(v.getCalado()));
			map.put("ma", String.valueOf(v.getMinutosHastaElAeropuerto()));
			map.put("l", String.valueOf(v.esPuntoDeAbastecimiento()));
			return v.getId() + "/" + v.getPais() + "\\n" + map.toString();
		}
		
	}
	
	private static class NameTrayectos implements EdgeNameProvider<Trayecto> {

		@Override
		public String getEdgeName(Trayecto e) {
			// TODO Auto-generated method stub
			//return arg0.getOrigen().getId() + " -- " + arg0.getDestino().getId();
			Map<String, String> map = new HashMap<>();
 			map.put("c", String.valueOf(e.getCosteTrayecto()));
 			map.put("d", String.valueOf(e.getDuracionEnDias()));
 			map.put("s", String.valueOf(e.getNivelDeSeguridad()));
 			return map.toString();
		}
		
	}
	
    private static class EtiquetasPuertos implements ComponentAttributeProvider<Puerto>{
	     	
 		public EtiquetasPuertos() {
 		}
 		
 		@Override
 		public Map<String, String> getComponentAttributes(Puerto v) {
 			Map<String, String> map = new HashMap<>();
				map.put("tasas", String.valueOf(v.getTasas()));
 			map.put("atractivo", String.valueOf(v.getAtractivo()));
 			map.put("calado", String.valueOf(v.getCalado()));
 			map.put("maeropueto", String.valueOf(v.getMinutosHastaElAeropuerto()));
 			map.put("abastecimiento", String.valueOf(v.esPuntoDeAbastecimiento()));
 			//map.put("style", "dotted");
   			return map;
 		}
 		
 	}
 	
 	private static class EtiquetasTrayectos implements ComponentAttributeProvider<Trayecto>{
 		     		
 		public EtiquetasTrayectos() {
 		}
 		
 		@Override
 		public Map<String, String> getComponentAttributes(Trayecto e) {
 			Map<String, String> map = new HashMap<>();
 			map.put("c", String.valueOf(e.getCosteTrayecto()));
 			map.put("d", String.valueOf(e.getDuracionEnDias()));
 			map.put("s", String.valueOf(e.getNivelDeSeguridad()));
			//map.put("style", "dotted");
 			return map;
 		}
 		
 	}  
}
