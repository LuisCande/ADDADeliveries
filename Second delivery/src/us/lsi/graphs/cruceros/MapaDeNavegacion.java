package us.lsi.graphs.cruceros;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.alg.VertexCovers;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.graphs.GraphsReader;

public class MapaDeNavegacion {

	private Graph<Puerto, Trayecto> g = null;

	private MapaDeNavegacion() {
		this(null);
	}

	private MapaDeNavegacion(Graph<Puerto, Trayecto> g) {
		this.g = g;
	}

	public static MapaDeNavegacion read(String file) {
		// DONE 1

		UndirectedGraph<Puerto, Trayecto> g = new SimpleGraph<Puerto, Trayecto>(Trayecto.factoria);
		g = (UndirectedGraph<Puerto, Trayecto>) GraphsReader.newGraph(file, Puerto.factoria, Trayecto.factoria, g);
		return new MapaDeNavegacion(g);
	}

	public Graph<Puerto, Trayecto> getMapa() {
		// DONE 2
		return g;
	}

	public Puerto getPuertoById(String id) {
		// DONE 3
		return g.vertexSet().stream().filter(p -> p.getId().equals(id)).findFirst().get();
	}

	public Set<Puerto> getRedDeControles() {
		// DONE 4
		return VertexCovers.find2ApproximationCover(getMapa());
	}

	public List<Set<Puerto>> getZonasDeInfluencia(int limiteDeDiasXDesplazamiento) {
		// DONE 5

		List<Set<Puerto>> res = new ArrayList<Set<Puerto>>();

		UndirectedGraph<Puerto, Trayecto> grafo = new SimpleGraph<Puerto, Trayecto>(Trayecto.factoria);

		getMapa().edgeSet().stream().filter(t -> (t.getDuracionEnDias() <= limiteDeDiasXDesplazamiento))
				.forEach(t -> grafoAuxiliar(t, grafo));

		ConnectivityInspector<Puerto, Trayecto> ins = new ConnectivityInspector<Puerto, Trayecto>(
				(UndirectedGraph<Puerto, Trayecto>) grafo);

		ins.connectedSets().stream().filter(s -> s.size() >= 3).forEach(s -> res.add(s));

		return res;

	}

	private void grafoAuxiliar(Trayecto t, Graph<Puerto, Trayecto> graph) {
		graph.addVertex(t.getDestino());
		graph.addVertex(t.getOrigen());
		graph.addEdge(t.getDestino(), t.getOrigen(), t);
	}

	public Set<Trayecto> getRedDeFerris() {
		// DONE 6
		KruskalMinimumSpanningTree<Puerto, Trayecto> x = new KruskalMinimumSpanningTree<>(getMapa());
		return x.getMinimumSpanningTreeEdgeSet();

	}

	public Integer getDuracionDeCrucero(List<Puerto> travesia) {
		// DONE 7
		
		Integer duracion = 0;
		for (Trayecto x : getMapa().edgeSet()) {
			for (Puerto v : travesia) {
				for (Puerto b : travesia) {
					if (v.equals(x.getOrigen()) && b.equals(x.getDestino())) {
						duracion += x.getDuracionEnDias();
					}
				}
			}
		}
		return duracion;

	}
	

	public Double getAtractivoMedio(List<Puerto> travesia) {
		// DONE 8
		Double suma = 0.0;
		Long numeroPuertos = travesia.stream().count();

		for (Puerto p : travesia) {
			suma += p.getAtractivo();
		}
		return suma / numeroPuertos;
	}

	public Boolean existeAlgunPuntoAbastecimiento(List<Puerto> travesia) {
		// DONE 9

		return travesia.stream().anyMatch(x -> x.esPuntoDeAbastecimiento());
		
	}

	public Double getCosteXCrucero(List<Puerto> travesia) {
		// TODO 10

		Double tasaPuertos = 0.0;
		for (Puerto p : travesia) {
			tasaPuertos = tasaPuertos + p.getTasas();
		}

		Double costeTrayecto = 0.0;
		for (Trayecto x : getMapa().edgeSet()) {
			for (Puerto v : travesia) {
				for (Puerto b : travesia) {
					if (v.equals(x.getOrigen()) && b.equals(x.getDestino())) {
						costeTrayecto += x.getCosteTrayecto();
					}
				}
			}
		}
		return tasaPuertos + costeTrayecto;

	}

	public Set<Puerto> getPuertosConAeropuertosCercanos() {
		// DONE 11

		return g.vertexSet().stream()
				.filter(x -> x.getMinutosHastaElAeropuerto() < 61 && x.getMinutosHastaElAeropuerto() > 0)
				.collect(Collectors.toSet());
	}

	public Set<List<Puerto>> getCrucerosValidos() {
		// DONE 12

		Set<List<Puerto>> res = new HashSet<List<Puerto>>();
		Set<Puerto> aeropuertosCercanos = new HashSet<Puerto>(this.getPuertosConAeropuertosCercanos());

		FloydWarshallShortestPaths<Puerto, Trayecto> caminos = new FloydWarshallShortestPaths<Puerto, Trayecto>(
				getGrafoPesos(g));

		caminos.getShortestPaths().stream().forEach(caminosMinimos -> res.add(caminosMinimos(caminosMinimos)));

		return res.stream().filter(
				x -> aeropuertosCercanos.contains(x.get(0)) && aeropuertosCercanos.contains(x.get(x.size() - 1)))
				.collect(Collectors.toSet());
	}

	private List<Puerto> caminosMinimos(GraphPath<Puerto, Trayecto> caminosMinimos) {
		List<Puerto> res = new ArrayList<Puerto>();
		res = Graphs.getPathVertexList(caminosMinimos);
		return res;
	}

	private SimpleWeightedGraph<Puerto, Trayecto> getGrafoPesos(Graph<Puerto, Trayecto> graph) {

		SimpleWeightedGraph<Puerto, Trayecto> res = new SimpleWeightedGraph<Puerto, Trayecto>(Trayecto.factoria);
		Graphs.addAllVertices(res, graph.vertexSet());

		for (Trayecto t : graph.edgeSet()) {
			res.setEdgeWeight(res.addEdge(graph.getEdgeSource(t), graph.getEdgeTarget(t)), t.getDuracionEnDias());
		}
		return res;
	}

	public Set<List<Puerto>> getCrucerosFantasticos() {
		// DONE 13
	
		return getCrucerosValidos().stream()
				.filter(x -> getDuracionDeCrucero(x) >= 9 && getDuracionDeCrucero(x) <= 12
						&& existeAlgunPuntoAbastecimiento(x) && getAtractivoMedio(x) >= 2.3)
				.collect(Collectors.toSet());
	}

}
