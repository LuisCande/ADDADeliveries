package ti4.jarras;

public class Accion {
	// Operaci�n a realizar
	private Operacion op;
	// Cantidad neta que se suma a la jarra 1 (negativo si se elimina)
	private int incJarra1;
	// Cantidad neta que se suma a la jarra 2 (negativo si se elimina)
	private int incJarra2;
	
	public Accion(Operacion op, int incJarra1, int incJarra2) {
		super();
		this.op = op;
		this.incJarra1 = incJarra1;
		this.incJarra2 = incJarra2;
	}

	public Operacion getOp() {
		return op;
	}

	public int getIncJarra1() {
		return incJarra1;
	}

	public int getIncJarra2() {
		return incJarra2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + incJarra1;
		result = prime * result + incJarra2;
		result = prime * result + ((op == null) ? 0 : op.hashCode());
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
		Accion other = (Accion) obj;
		if (incJarra1 != other.incJarra1)
			return false;
		if (incJarra2 != other.incJarra2)
			return false;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return op.getCodigo().toString() ;
	}
	 
		 
}