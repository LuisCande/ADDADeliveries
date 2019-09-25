package ti4.jarras.bt;

import ti4.jarras.*;
import us.lsi.bt.ProblemaBT;

public class ProblemaJarraBT implements ProblemaBT<SolucionJarra,Accion>{

	public static ProblemaJarraBT create() {
		return new ProblemaJarraBT();
	}

	private ProblemaJarraBT() {
		super();
	}
	
	@Override
	public EstadoJarra getEstadoInicial() {
		return EstadoJarra.create();
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}
}
