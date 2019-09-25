package ti4.jarras;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import us.lsi.stream.Stream2;

public class ProblemaJarra {
	
	public static ProblemaJarra create(String fileJarras, String fileOperaciones) {		
		return new ProblemaJarra(fileJarras, fileOperaciones);
	}
	
	
	
	public static Jarra jarra1;
	public static Jarra jarra2;
	
	public static List<Operacion> operaciones;
	
	public static Integer cantidadFinalEnJarra1;
	public static Integer cantidadFinalEnJarra2;
	
	public static Integer numMaxOperaciones;
	
	public ProblemaJarra(String fileJarras, String fileOperaciones) {
		super();
		leeJarras(fileJarras);
		leeOperaciones(fileOperaciones);
	}

	private void leeOperaciones(String fileOperaciones) {
		operaciones = Stream2.fromFile(fileOperaciones)
				.<Operacion> map((String s) -> Operacion.create(s))
				.collect(Collectors.<Operacion> toList());
		
	}

	private void leeJarras(String file) {
		List<String> ls = Stream2.fromFile(file).toList();
		Preconditions.checkArgument(ls.size()==2);
		jarra1 = Jarra.create(ls.get(0));
		jarra2 = Jarra.create(ls.get(1));
		
		
	}
	
	

}
