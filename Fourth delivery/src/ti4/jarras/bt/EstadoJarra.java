package ti4.jarras.bt;

import java.util.*;
import com.google.common.collect.Lists;

import ti4.jarras.*;
import us.lsi.bt.EstadoBT;

public class EstadoJarra implements EstadoBT<SolucionJarra, Accion> {

	public static Double mejorValorObtenido = Double.MAX_VALUE;
	public static SolucionJarra mejorSolucionObtenida = null;

	public static EstadoJarra create() {
		return new EstadoJarra();
	}

	public static EstadoJarra create(List<Operacion> lista) {
		return new EstadoJarra(0, 0, lista);
	}

	private SolucionJarra lista;
	private static List<Operacion> operaciones;
	private static int numMaxOperaciones;
	private int operacionesRealizadas;
	private int cantidadAcumuladaJarra1;
	private int cantidadAcumuladaJarra2;
	private static int cantidadFinalEnJarra1;
	private static int cantidadFinalEnJarra2;
	private static Jarra jarra2;
	private static Jarra jarra1;

	private EstadoJarra() {
		super();
		jarra1 = ProblemaJarra.jarra1;
		jarra2 = ProblemaJarra.jarra2;

		cantidadFinalEnJarra1 = ProblemaJarra.cantidadFinalEnJarra1;
		cantidadFinalEnJarra2 = ProblemaJarra.cantidadFinalEnJarra2;
		operaciones = ProblemaJarra.operaciones;
		numMaxOperaciones = ProblemaJarra.numMaxOperaciones;

		this.cantidadAcumuladaJarra1 = 0;
		this.cantidadAcumuladaJarra2 = 0;
		this.operacionesRealizadas = 0;
		this.lista = SolucionJarra.create(Lists.newArrayList());

	}

	private EstadoJarra(Integer ac1, Integer ac2, List<Operacion> lista) {
		super();

		this.cantidadAcumuladaJarra1 = ac1;
		this.cantidadAcumuladaJarra2 = ac2;
		this.operacionesRealizadas = lista.size();
		this.lista = SolucionJarra.create(lista);
	}

	public void avanza(Accion a) {

		this.cantidadAcumuladaJarra1 += a.getIncJarra1();
		this.cantidadAcumuladaJarra2 += a.getIncJarra2();
		this.operacionesRealizadas += 1;
		List<Operacion> copia = Lists.newArrayList(lista.getListaOperaciones());
		copia.add(a.getOp());
		this.lista = SolucionJarra.create(copia);

	}

	public void retrocede(Accion a) {

		this.cantidadAcumuladaJarra1 -= a.getIncJarra1();
		this.cantidadAcumuladaJarra2 -= a.getIncJarra2();
		this.operacionesRealizadas -= 1;
		List<Operacion> copia = Lists.newArrayList(lista.getListaOperaciones());
		copia.remove(lista.getListaOperaciones().size() - 1);
		this.lista = SolucionJarra.create(copia);
	}

	public int size() {
		return numMaxOperaciones - this.operacionesRealizadas;
	}

	public boolean isFinal() {
		Boolean casobase1 = this.cantidadAcumuladaJarra1 == cantidadFinalEnJarra1
				&& this.cantidadAcumuladaJarra2 == cantidadFinalEnJarra2;
		return casobase1;

	}

	public List<Accion> getAlternativas() {
		List<Accion> alternativas = new LinkedList<Accion>();
		Boolean casobase2 = numMaxOperaciones <= this.operacionesRealizadas;

		if (casobase2) {
			return alternativas;
		}

		for (Operacion o : operaciones) {
			Integer codigo = o.getCodigo();
			// Vaciar J1
			if (codigo == 0) {

				Integer incrementoJarra1 = -this.cantidadAcumuladaJarra1;
				Integer incrementoJarra2 = 0;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Volcar J1 en J2
			} else if (codigo == 1) {

				Integer incrementoJarra1 = -this.cantidadAcumuladaJarra1;
				Integer maximoIncremento = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = maximoIncremento < this.cantidadAcumuladaJarra1 ? maximoIncremento
						: this.cantidadAcumuladaJarra1;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Echar J1 en J2
			} else if (codigo == 2) {

				Integer maximoIncremento = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = (maximoIncremento < this.cantidadAcumuladaJarra1) ? maximoIncremento
						: this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = -incrementoJarra2;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Llenar J1
			} else if (codigo == 3) {

				Integer incrementoJarra2 = 0;
				Integer incrementoJarra1 = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Llenar J2
			} else if (codigo == 4) {
				Integer incrementoJarra1 = 0;
				Integer incrementoJarra2 = jarra2.getCapacidad() - this.cantidadAcumuladaJarra2;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Vaciar J2
			} else if (codigo == 5) {

				Integer incrementoJarra2 = -this.cantidadAcumuladaJarra2;
				Integer incrementoJarra1 = 0;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Volcar J2 en J1
			} else if (codigo == 6) {

				Integer incrementoJarra2 = -this.cantidadAcumuladaJarra2;
				Integer maximoIncremento = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = maximoIncremento < this.cantidadAcumuladaJarra2 ? maximoIncremento
						: this.cantidadAcumuladaJarra2;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);

				// Echar J2 en J1
			} else if (codigo == 7) {

				Integer maximoIncremento = jarra1.getCapacidad() - this.cantidadAcumuladaJarra1;
				Integer incrementoJarra1 = (maximoIncremento < this.cantidadAcumuladaJarra2) ? maximoIncremento
						: this.cantidadAcumuladaJarra2;
				Integer incrementoJarra2 = -incrementoJarra1;

				if (incrementoJarra1 == 0 && incrementoJarra2 == 0)
					continue;
				Accion a = new Accion(o, incrementoJarra1, incrementoJarra2);
				alternativas.add(a);
			}
		}

		return alternativas;
	}

	public SolucionJarra getSolucion() {
		return this.lista;
	}

	public Double getObjetivo() {
		return (double) operacionesRealizadas;
	}

	public Double getObjetivoEstimado(Accion a) {
		return Double.MIN_VALUE;
	}

	// FUNCION DE COTA

	public Integer cotaEstimada(Accion a) {

		Integer copiaCantidadAcumulada1 = this.cantidadAcumuladaJarra1;
		Integer copiaCantidadAcumulada2 = this.cantidadAcumuladaJarra2;

		copiaCantidadAcumulada1 += a.getIncJarra1();
		copiaCantidadAcumulada2 += a.getIncJarra2();
		
		if (copiaCantidadAcumulada1 == cantidadFinalEnJarra1 && copiaCantidadAcumulada2 == cantidadFinalEnJarra2) {
			return 1;
		}

		for (Operacion op1 : operaciones) {
			for (Operacion op2 : operaciones) {
				for (Operacion op3 : operaciones) {

					Map<String, Integer> m1 = obtieneIncremento(op1, copiaCantidadAcumulada1, copiaCantidadAcumulada2);
					copiaCantidadAcumulada1 += m1.get("incremento1");
					copiaCantidadAcumulada2 += m1.get("incremento2");
					
					if (copiaCantidadAcumulada1 == cantidadFinalEnJarra1
							&& copiaCantidadAcumulada2 == cantidadFinalEnJarra2) {
						return 2;
					}

					Map<String, Integer> m2 = obtieneIncremento(op2, copiaCantidadAcumulada1, copiaCantidadAcumulada2);
					copiaCantidadAcumulada1 += m2.get("incremento1");
					copiaCantidadAcumulada2 += m2.get("incremento2");

					if (copiaCantidadAcumulada1 == cantidadFinalEnJarra1
							&& copiaCantidadAcumulada2 == cantidadFinalEnJarra2) {
						return 3;
					}

					Map<String, Integer> m3 = obtieneIncremento(op3, copiaCantidadAcumulada1, copiaCantidadAcumulada2);
					copiaCantidadAcumulada1 += m3.get("incremento1");
					copiaCantidadAcumulada2 += m3.get("incremento2");

					if (copiaCantidadAcumulada1 == cantidadFinalEnJarra1
							&& copiaCantidadAcumulada2 == cantidadFinalEnJarra2) {
						return 4;
					}
				}
			}
		}
		return (int) Double.MIN_VALUE;
	}

	private Map<String, Integer> obtieneIncremento(Operacion o, Integer acumuladoJarra1, Integer acumuladoJarra2) {
		
		Integer maxIncr = 0;
		Integer incJarra1 = 0;
		Integer incJarra2 = 0;
		Map<String, Integer> mapa = new HashMap<String, Integer>();

		Integer codigo = o.getCodigo();

		// Vaciar J1 
		if (codigo == 0) {
			incJarra1 = -acumuladoJarra1;
			incJarra2 = 0;
		}

		// Volcar J1 en J2
		if (codigo == 1) {
			incJarra1 = -acumuladoJarra1;
			maxIncr = jarra2.getCapacidad() - acumuladoJarra2;
			incJarra2 = maxIncr < incJarra1 ? maxIncr : incJarra1;
		}

		// Echar J1 en J2
		if (codigo == 2) {
			maxIncr = jarra2.getCapacidad() - acumuladoJarra2;
			incJarra2 = (maxIncr < acumuladoJarra1) ? maxIncr : acumuladoJarra1;
			incJarra1 = -incJarra2;
		}

		// Llenar J1
		if (codigo == 3) {
			incJarra1 = jarra1.getCapacidad() - acumuladoJarra1;
			incJarra2 = 0;
		}

		// Llenar la J2
		if (codigo == 4) {
			incJarra1 = 0;
			incJarra2 = jarra2.getCapacidad() - acumuladoJarra2;
		}

		// Vaciar J2
		if (codigo == 5) {
			incJarra2 = -acumuladoJarra2;
			incJarra1 = 0;
		}

		// Volcar J2 en J1
		if (codigo == 6) {
			incJarra2 = -acumuladoJarra2;
			maxIncr = jarra1.getCapacidad() - acumuladoJarra1;
			incJarra1 = maxIncr < acumuladoJarra2 ? maxIncr : acumuladoJarra2;
		}
		
		// Echar J2 en J1
		if (codigo == 7) {
			maxIncr = jarra1.getCapacidad() - acumuladoJarra1;
			incJarra1 = (maxIncr < acumuladoJarra2) ? maxIncr : acumuladoJarra2;
			incJarra2 = -incJarra1;
		}

		mapa.put("incremento1", incJarra1);
		mapa.put("incremento2", incJarra2);
		return mapa;
	}

}
