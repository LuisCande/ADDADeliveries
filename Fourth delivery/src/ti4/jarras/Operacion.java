package ti4.jarras;

public class Operacion {
	
	public static Operacion create(String s){
		return new Operacion(s);
	}
	
	public static Operacion create(Integer codigo, String operacion, String descripcion){
		return new Operacion(codigo, operacion, descripcion);
	}
	private Integer codigo;
	private String operacion;
	private String descripcion;
	
	public Operacion(String s){
		String[] v = s.split(",");
		Integer ne = v.length;
		if(ne != 3) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
		this.codigo = new Integer(v[0]);
		this.operacion = v[1];
		this.descripcion = v[2];
		
	}
	public Operacion(Integer codigo, String operacion, String descripcion) {
		super();
		this.codigo = codigo;
		this.operacion = operacion;
		this.descripcion = descripcion;
	}
	public Operacion(){
		super();
		this.codigo = -1;
		this.operacion = "NOP";
		this.descripcion = "no se hace nada";
		
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((operacion == null) ? 0 : operacion.hashCode());
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
		Operacion other = (Operacion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (operacion == null) {
			if (other.operacion != null)
				return false;
		} else if (!operacion.equals(other.operacion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Operacion [codigo=" + codigo + ", operacion=" + operacion + ", descripcion=" + descripcion + "]\n";
	}
	
	
	

}
