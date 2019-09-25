package ti4.jarras;

public class Jarra {
	
	public static Jarra create(Integer codigo, Integer capacidad){
		return new Jarra(codigo, capacidad);
	}
	
	/**
	 * @param s Una línea de un fichero de texto
	 * @return Construye una jarra a partir de una línea de un fichero
	 */
	public static Jarra create(String s){
		return new Jarra(s);
	}
	
	private Integer codigo;
	private Integer capacidad;
	
	public Jarra(String s){
		String[] v = s.split("[ ,]");
		Integer ne = v.length;
		if(ne != 2) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		this.codigo = new Integer(v[0]);
		this.capacidad = new Integer(v[1]);
	}
	
	public Jarra(Integer codigo, Integer capacidad) {
		super();
		this.codigo = codigo;
		this.capacidad = capacidad;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacidad == null) ? 0 : capacidad.hashCode());
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
		Jarra other = (Jarra) obj;
		if (capacidad == null) {
			if (other.capacidad != null)
				return false;
		} else if (!capacidad.equals(other.capacidad))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jarra [codigo=" + codigo + ", capacidad=" + capacidad + "]";
	}
	
}
