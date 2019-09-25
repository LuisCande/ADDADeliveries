package ti3.vagones;

import java.util.*;
import us.lsi.stream.Stream2;

public class ProblemaVagones {

	public static List<Vagon> listaVagones; 
	public static Integer pesoTotal;
	public static Double sumaValor;
	
	public ProblemaVagones(String file) {
		super();
		leeVagones(file);
	}
	
	public static void leeVagones(String file){	
		List<String> ls = Stream2.fromFile(file).toList();
		listaVagones = new ArrayList<Vagon>();
		sumaValor = 0.0;
		int index = 0;
		for(String s : ls){
			String[] at = Stream2.fromString(s, ",").toArray((int x)->new String[x]);
			Vagon v = Vagon.create(index, at);
			listaVagones.add(v);
			sumaValor += v.getValor() * v.getNumMaxDeUnidades();
			index++;
		}
	}

	public static ProblemaVagones create(String file) {		
		return new ProblemaVagones(file);
	}

}
