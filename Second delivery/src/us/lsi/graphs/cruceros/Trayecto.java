package us.lsi.graphs.cruceros;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;

import us.lsi.graphs.StringEdgeFactory;

public class Trayecto extends DefaultWeightedEdge {

	public static Factoria factoria = new Factoria();
	
	private static final long serialVersionUID = 1L;

	
	static class Factoria implements StringEdgeFactory<Puerto,Trayecto>, EdgeFactory<Puerto,Trayecto> {
		
	
	public Trayecto createEdge(Puerto c1, Puerto c2) {
        return new Trayecto(c1,c2);
		
	}


	public Trayecto createEdge(Puerto c1, Puerto c2, String[] formato) {
		return new Trayecto(c1,c2,formato);
	}
}
	
	
	private static  Double doubleValueOf(String[] formato, int pos) {
		if (pos<formato.length) {
			return Double.valueOf(formato[pos]);
		}
		return null;
	}
	
	private static  Integer intValueOf(String[] formato, int pos) {
		if (pos<formato.length) {
			return Integer.valueOf(formato[pos]);
		}
		return null;
	}
	
	private Puerto origen = null;
	private Puerto destino = null;
	private Double costeTrayecto =0.0;
	private Integer duracionEnDias = 0;
	private Integer nivelDeSeguridad = null;
	
	
	public Trayecto() {
		super();
	}
	
	public Trayecto(Trayecto d) {
		this(d.origen,d.destino,d.costeTrayecto,d.duracionEnDias,d.nivelDeSeguridad);
	}
	
	public Trayecto(Puerto origen, Puerto destino) {
		this.origen=origen;
		this.destino=destino;
	}
	
	public Trayecto(Puerto origen, Puerto destino, String[] formato) {
		this(origen,destino,
				doubleValueOf(formato,2),
				intValueOf(formato,3),
				intValueOf(formato,4));
		
		if (formato.length<5) { System.out.println(this + "->" + "Numero de campos erroneos"); }
		if (this.getCosteTrayecto()>100) { System.out.println(this + "->" + "ERR[2]: coste"  + this.getCosteTrayecto()); }
		if (this.getDuracionEnDias()>5) { System.out.println(this + "->" + "ERR[3]: duracion"  + this.getDuracionEnDias()); }
		if (this.getNivelDeSeguridad()>3) { System.out.println(this + "->" + "ERR[4]: seguridad " + this.getNivelDeSeguridad()); }
	}
	
	public Trayecto(Puerto origen, Puerto destino, Double costeTrayecto,
			Integer duracionEnDias, Integer nivelDeSeguridad) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.costeTrayecto = costeTrayecto;
		this.duracionEnDias = duracionEnDias;
		this.nivelDeSeguridad = nivelDeSeguridad;
	}
	
	
	
	public Puerto getOrigen() {
		return origen;
	}

	public Puerto getDestino() {
		return destino;
	}

	public Double getCosteTrayecto() {
		return costeTrayecto;
	}

	public Integer getDuracionEnDias() {
		return duracionEnDias;
	}

	public Integer getNivelDeSeguridad() {
		return nivelDeSeguridad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trayecto other = (Trayecto) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[origen=" + origen + ", destino=" + destino
				+ "]";
	}


	
	
}
