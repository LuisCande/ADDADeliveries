package ti3.vagones;

import java.util.*;

public class SolucionVagones {

	private Map<Vagon, Integer> vagones;
	private Integer peso;	
	private Double valor;	

	public static SolucionVagones create(Map<Vagon, Integer> vagones) {
		return new SolucionVagones(vagones);
	}

	private SolucionVagones(Map<Vagon, Integer> vagones){
		this.vagones = new HashMap<Vagon, Integer>();
		this.vagones.putAll(vagones);
		calculaPropiedadesDerivadas();		
	}
	
	private void calculaPropiedadesDerivadas(){
		this.peso = 0;
		this.valor = 0.;
		for (Vagon c: vagones.keySet()){
			this.valor = this.valor + c.getValor()*vagones.get(c);
			this.peso = this.peso + c.getPeso()*vagones.get(c);
		}
	}
	
	public Map<Vagon, Integer> getVagones() {
		return vagones;
	}

	public Double getValor() {
		return valor;
	}
	
	public Integer getPeso() {
		return peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vagones == null) ? 0 : vagones.hashCode());
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
		SolucionVagones other = (SolucionVagones) obj;
		if (vagones == null) {
			if (other.vagones != null)
				return false;
		} else if (!vagones.equals(other.vagones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SolucionVagones [vagones=" + vagones + ", peso=" + peso
				+ ", valor=" + valor + "]";
	}
		
}
