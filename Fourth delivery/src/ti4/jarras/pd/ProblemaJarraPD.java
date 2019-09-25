package ti4.jarras.pd;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import com.google.common.base.Preconditions;

import ti4.jarras.*;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaJarraPD implements ProblemaPD<SolucionJarra, Accion> {

	
	//Propiedades Individuales 
	
	private Integer cantidadAcumuladaJarra1;
	private Integer cantidadAcumuladaJarra2;
	private int operacionesRealizadas;
	
	//Propiedades Compartidas
	
	public static Jarra jarra1;
	public static Jarra jarra2;
	public static List<Operacion> operaciones;
	public static Integer numMaxOperaciones;
	private static Integer cantidadFinalEnJarra1;
	private static Integer cantidadFinalEnJarra2;

	

	public static ProblemaJarraPD create() {
		return new ProblemaJarraPD(0, 0, 0);
	}

	public ProblemaJarraPD(int cantidadAcumuladaJarra1, int cantidadAcumuladaJarra2, int operacionesRealizadas) {

		operaciones = ProblemaJarra.operaciones;
		jarra1 = ProblemaJarra.jarra1;
		jarra2 = ProblemaJarra.jarra2;
		cantidadFinalEnJarra1 = ProblemaJarra.cantidadFinalEnJarra1;
		cantidadFinalEnJarra2 = ProblemaJarra.cantidadFinalEnJarra2;
		numMaxOperaciones = ProblemaJarra.numMaxOperaciones;
		this.cantidadAcumuladaJarra1 = cantidadAcumuladaJarra1;
		this.cantidadAcumuladaJarra2 = cantidadAcumuladaJarra2;
		this.operacionesRealizadas = operacionesRealizadas;
	}

	public us.lsi.pd.ProblemaPD.Tipo getTipo() {
		return Tipo.Min;
	}

	public int size() {
		return numMaxOperaciones - this.operacionesRealizadas;
	}

	public boolean esCasoBase() {
		Boolean casoBase1 = this.cantidadAcumuladaJarra1 == cantidadFinalEnJarra1
				&& this.cantidadAcumuladaJarra2 == cantidadFinalEnJarra2;
		Boolean casoBase2 = numMaxOperaciones < this.operacionesRealizadas;

		return casoBase1 || casoBase2;
	}

	public Sp<Accion> getSolucionCasoBase() {

		if (this.cantidadAcumuladaJarra1 == cantidadFinalEnJarra1
				&& this.cantidadAcumuladaJarra2 == cantidadFinalEnJarra2) {

			return Sp.create(null, 0.);
		}
		return null;
	}

	public Sp<Accion> seleccionaAlternativa(List<Sp<Accion>> ls) {

		Sp<Accion> r = ls.stream().filter(x -> x.propiedad != null).min(Comparator.naturalOrder()).orElse(null);

		return r;

	}

	public ProblemaPD<SolucionJarra, Accion> getSubProblema(Accion a, int np) {
		Preconditions.checkArgument(np == 0);

		Integer cantidadActualizadaJarra1 = this.cantidadAcumuladaJarra1 + a.getIncJarra1();
		Integer cantidadActualizadaJarra2 = this.cantidadAcumuladaJarra2 + a.getIncJarra2();
		Integer numOperaciones = this.operacionesRealizadas + 1;

		return new ProblemaJarraPD(cantidadActualizadaJarra1, cantidadActualizadaJarra2, numOperaciones);
	}

	public Sp<Accion> combinaSolucionesParciales(Accion a, List<Sp<Accion>> ls) {
		Double p = ls.get(0).propiedad + 1.;
		return Sp.create(a, p);

	}

	public List<Accion> getAlternativas() {
		List<Accion> alternativas = new LinkedList<Accion>();

		for (Operacion o : operaciones) {

			Integer codigo = o.getCodigo();

			if (codigo == 0 && cantidadAcumuladaJarra1 != 0) {
				Integer incrementoJarra1 = -this.cantidadAcumuladaJarra1;
				Integer incrementoJarra2 = 0;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 1 && this.cantidadAcumuladaJarra1 != 0) {

				Integer incrementoJarra1 = -this.cantidadAcumuladaJarra1;
				Integer maximoIncremento = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = maximoIncremento < this.cantidadAcumuladaJarra1 ? maximoIncremento
						: this.cantidadAcumuladaJarra1;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 2 && this.cantidadAcumuladaJarra1 != 0
					&& this.cantidadAcumuladaJarra2 != jarra2.getCapacidad()) {

				Integer maximoIncremento = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = (maximoIncremento < this.cantidadAcumuladaJarra1) ? maximoIncremento
						: this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = -incrementoJarra2;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 3 && this.cantidadAcumuladaJarra1 != jarra1.getCapacidad()) {
				Integer incrementoJarra2 = 0;
				Integer incrementoJarra1 = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 4 && this.cantidadAcumuladaJarra2 != jarra2.getCapacidad()) {
				Integer incrementoJarra1 = 0;
				Integer incrementoJarra2 = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 5 && cantidadAcumuladaJarra2 != 0) {

				Integer incrementoJarra2 = -this.cantidadAcumuladaJarra2;
				Integer incrementoJarra1 = 0;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 6 && this.cantidadAcumuladaJarra2 != 0) {

				Integer incrementoJarra2 = -this.cantidadAcumuladaJarra2;
				Integer maximoIncremento = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = maximoIncremento < this.cantidadAcumuladaJarra2 ? maximoIncremento
						: this.cantidadAcumuladaJarra2;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

			} else if (codigo == 7 && this.cantidadAcumuladaJarra2 != 0
					&& this.cantidadAcumuladaJarra1 != jarra1.getCapacidad()) {

				Integer maximoIncremento = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = (maximoIncremento < this.cantidadAcumuladaJarra2) ? maximoIncremento
						: this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = -incrementoJarra1;

				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);
			}
		}

		return alternativas;
	}

	public int getNumeroSubProblemas(Accion a) {
		return 1;
	}

	public SolucionJarra getSolucionReconstruida(Sp<Accion> sp) {
		List<Operacion> nueva = new LinkedList<Operacion>();
		return SolucionJarra.create(nueva);
	}

	public SolucionJarra getSolucionReconstruida(Sp<Accion> sp, List<SolucionJarra> ls) {
		SolucionJarra solucion = ls.get(0);
		List<Operacion> noesnueva = solucion.getListaOperaciones();
		Accion realizada = sp.alternativa;
		noesnueva.add(realizada.getOp());
		return SolucionJarra.create(noesnueva);

	}

	public Double getObjetivoEstimado(Accion a) {
		return Double.MIN_VALUE;
	}

	public Double getObjetivo() {
		return Double.MAX_VALUE;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadAcumuladaJarra1 == null) ? 0 : cantidadAcumuladaJarra1.hashCode());
		result = prime * result + ((cantidadAcumuladaJarra2 == null) ? 0 : cantidadAcumuladaJarra2.hashCode());
		result = prime * result + operacionesRealizadas;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaJarraPD other = (ProblemaJarraPD) obj;
		if (cantidadAcumuladaJarra1 == null) {
			if (other.cantidadAcumuladaJarra1 != null)
				return false;
		} else if (!cantidadAcumuladaJarra1.equals(other.cantidadAcumuladaJarra1))
			return false;
		if (cantidadAcumuladaJarra2 == null) {
			if (other.cantidadAcumuladaJarra2 != null)
				return false;
		} else if (!cantidadAcumuladaJarra2.equals(other.cantidadAcumuladaJarra2))
			return false;
		if (operacionesRealizadas != other.operacionesRealizadas)
			return false;
		return true;
	}

}
