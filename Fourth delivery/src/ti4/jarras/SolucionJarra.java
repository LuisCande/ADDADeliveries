package ti4.jarras;

import java.util.List;

import com.google.common.collect.Lists;

public class SolucionJarra implements Comparable<SolucionJarra> {

	public static SolucionJarra create() {
		return new SolucionJarra();
	}

	public static SolucionJarra create(List<Operacion> m) {
		return new SolucionJarra(m);
	}

	private Integer numOp;
	private List<Operacion> listaOperaciones;

	public SolucionJarra(List<Operacion> ls) {
		super();
		this.numOp = ls.size();
		this.listaOperaciones = Lists.newArrayList(ls);
	}

	public SolucionJarra() {
		super();
		this.numOp = 0;
		this.listaOperaciones = Lists.newArrayList();
	}

	public void add(Operacion op) {
		listaOperaciones.add(op);
	}

	public Operacion removeLast() {
		if (listaOperaciones.isEmpty())
			return null;
		return listaOperaciones.remove(listaOperaciones.size() - 1);
	}

	public Integer getNumOp() {
		return listaOperaciones.size();
	}

	public List<Operacion> getListaOperaciones() {
		return listaOperaciones;
	}

	public void setListaOperaciones(List<Operacion> ls) {
		this.listaOperaciones = ls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaOperaciones == null) ? 0 : listaOperaciones.hashCode());
		result = prime * result + ((numOp == null) ? 0 : numOp.hashCode());
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
		SolucionJarra other = (SolucionJarra) obj;
		if (listaOperaciones == null) {
			if (other.listaOperaciones != null)
				return false;
		} else if (!listaOperaciones.equals(other.listaOperaciones))
			return false;
		if (numOp == null) {
			if (other.numOp != null)
				return false;
		} else if (!numOp.equals(other.numOp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SolucionJarra [numOp=" + numOp + ", ls=" + listaOperaciones.toString() + "]";
	}

	@Override
	public int compareTo(SolucionJarra o) {
		int r = getNumOp().compareTo(o.getNumOp());
		if (r == 0) {
			r = this.toString().compareTo(o.toString());
		}
		return r;
	}

}
