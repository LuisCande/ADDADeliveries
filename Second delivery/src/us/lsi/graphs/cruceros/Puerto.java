package us.lsi.graphs.cruceros;

import us.lsi.graphs.StringVertexFactory;

public class Puerto {

	static public StringVertexFactory<Puerto> factoria = new StringVertexFactory<Puerto>() {
		
	
	public Puerto createVertex(String[] formato) {
		
		return new Puerto(formato[0],formato[1],Double.valueOf(formato[2]),Integer.parseInt(formato[3]),Integer.parseInt(formato[4]),
				Integer.parseInt(formato[5]),Boolean.valueOf(formato[6]));
	}
	};
	
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

	private static  Boolean boolValueOf(String[] formato, int pos) {
		if (pos<formato.length) {
			return Boolean.valueOf(formato[pos]);
		}
		return null;
	}

	
	private String id = null;
	private String pais = null;
	private Double tasas = null;
	private Integer minutosHastaElAeropuerto = null;
	private Integer atractivo = null; 
	private Integer calado = null;	
	private Boolean esPuntoDeAbastecimiento = null; 
	
	public Puerto(Puerto p) {
		this(p.id, p.pais,p.tasas, p.minutosHastaElAeropuerto, p.atractivo,p.calado, p.esPuntoDeAbastecimiento); 
	}
	
	public Puerto(String id, String pais, String[] formato) {
		this(formato[0],
			 formato[1],
			 doubleValueOf(formato,2),
			 intValueOf(formato,3),
			 intValueOf(formato,4),
			 intValueOf(formato,5),
			 boolValueOf(formato,6)
		);
		if (formato.length<6) { System.out.println(this + "->" + "Numero de campos erroneos"); }
		if (this.getTasas()>40) { System.out.println(this + "->" + "ERR[2]: Tasas. " + this.getTasas()); }		
		if (this.getMinutosHastaElAeropuerto()>100) { System.out.println(this + "->" + "ERR[3]: Minutos hasta el aero. " + this.getMinutosHastaElAeropuerto()); }
		if (this.getAtractivo()>4) { System.out.println(this + "->" + "ERR[4]: Atractivo. " + this.getAtractivo()); }
        if (this.getCalado()>30) { System.out.println(this + "->" + "ERR[5]: Calado. " + this.getCalado()); }		
	}
	
	public Puerto(String id, String pais, Double tasas, Integer minutosHastaElAeropuerto,
			Integer atractivo, Integer calado, Boolean esPuntoDeAbastecimiento) {
		super();
		this.id = id;
		this.pais = pais;
		this.tasas = tasas;
		this.minutosHastaElAeropuerto = minutosHastaElAeropuerto;
		this.atractivo = atractivo;
		this.calado = calado;  
		this.esPuntoDeAbastecimiento = esPuntoDeAbastecimiento; 
	}

	public String getId() {
		return id;
	}
	
	public String getPais() {
		return pais;
	}

	public Double getTasas() {
		return tasas;
	}

	public Integer getMinutosHastaElAeropuerto() {
		return minutosHastaElAeropuerto;
	}

	public Integer getAtractivo() {
		return atractivo;
	}

	public Integer getCalado() {
		return calado;
	}

	public Boolean esPuntoDeAbastecimiento() {
		return esPuntoDeAbastecimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Puerto other = (Puerto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id.replace(" ", "_");
	}

	

}
