package ti3.vagones.pl;

import java.util.List;

import ti3.vagones.ProblemaVagones;
import ti3.vagones.Vagon;

public class VagonesPLI {
	// TODO
	public static String getConstraints() {
		String res = "max:";

		// FUNCION OBJETIVO
		List<Vagon> lista = ProblemaVagones.listaVagones;
		for (Vagon v : lista) {
			res += " +" + v.getValor() + "x" + v.getCodigo();
		}

		res += ";\n";
		

		// EXCEPCION UNIDADES DISPONIBLES

		for (Vagon v : lista) {
			res += "x" + v.getCodigo() + " <= " + v.getNumMaxDeUnidades() + ";\n";
		}
	
		// EXCEPCION PESO

		for (Vagon v : lista) {
			res += " + " + v.getPeso() + "x" + v.getCodigo();
		}
		res += " <=" + ProblemaVagones.pesoTotal+";\n";
		
		// EXCEPCION VIGILANCIA

		for (Vagon v : lista) {
			if (v.isVigilancia()) {
				res += " + x" + v.getCodigo();
			}
		}
		res += " <=1;\n";
		

		// EXCEPCION ELECTRICIDAD

		for (Vagon v : lista) {
			if (v.isElectricidad()) {
				res += " + x" + v.getCodigo();
			}
		}
		res += " <=2;\n";
		
		// DECLARACION DE VARIABLES

		res += "int ";

		for (Vagon v : lista) {
			if (v.getCodigo() == ProblemaVagones.listaVagones.size()-1) {
				res += "x"+v.getCodigo()+";";
			} else {
				res += "x" + v.getCodigo() + ",";
			}
			
		}
		
		return res;
		
	}
	

}
