package ti3.vagones;

public class Vagon{

	private Integer codigo;
	private Integer peso;
	private Double valor;
	private boolean vigilancia;
	private boolean electricidad;
	private Integer numMaxDeUnidades;
	
	private Vagon(Integer codigo, Integer peso, Double valor,
			boolean vigilancia, boolean electricidad, int numMax) {
		super();
		this.codigo = codigo;
		this.peso = peso;
		this.valor = valor;
		this.vigilancia = vigilancia;
		this.electricidad = electricidad;
		this.numMaxDeUnidades = numMax;
	}
	
	private Vagon(int codigo, String[] fm) {
		super();
		this.codigo = codigo;
		this.peso = new Integer(fm[0]);
		this.valor = new Double(fm[1]);;
		this.vigilancia = new Boolean(fm[2]);;
		this.electricidad = new Boolean(fm[3]);;
		this.numMaxDeUnidades = new Integer(fm[4]);
	}
	
	public static Vagon create(Integer codigo, Integer peso, Double valor,
			boolean vigilancia, boolean electricidad, int numMax) {
		return new Vagon(codigo, peso, valor, vigilancia, electricidad, numMax);
	}
	
	public static Vagon create(int codigo, String[] fm) {
		return new Vagon(codigo, fm);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Vagon other = (Vagon) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vagón " + codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Integer getPeso() {
		return peso;
	}

	public Double getValor() {
		return valor;
	}

	public boolean isVigilancia() {
		return vigilancia;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public Integer getNumMaxDeUnidades() {
		return numMaxDeUnidades;
	}

}

