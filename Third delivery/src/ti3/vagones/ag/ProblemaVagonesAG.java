package ti3.vagones.ag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ti3.vagones.ProblemaVagones;
import ti3.vagones.SolucionVagones;
import ti3.vagones.Vagon;
import us.lsi.ag.ProblemaAGIndex;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.common.Lists2;


public class ProblemaVagonesAG implements ProblemaAGIndex<SolucionVagones>{

	
	public Integer getObjectsNumber() {
		return ProblemaVagones.listaVagones.size();
	}

	
	
	public Double fitnessFunction(IndexChromosome cr) {
		
		//System.out.println(cr.decode());
		
		Integer numeroVagonesElectricidad = 0;
		Integer numeroVagonesVigilancia= 0;
		Double fitness=0.0;
		Integer indice= 0;
		Double peso=0.0;
		Integer indice1 = 0;
		Double valor=0.0;
		
		for(Integer i:cr.decode()){
			if(ProblemaVagones.listaVagones.get(indice1).isElectricidad()){
				
			numeroVagonesElectricidad += i;
		}
		    if(ProblemaVagones.listaVagones.get(indice1).isVigilancia()){
		    numeroVagonesVigilancia += i;
		    }
		    indice1++;
		}
		
		if(numeroVagonesElectricidad>2 || numeroVagonesVigilancia >1){
			fitness= -1000000.0*(numeroVagonesElectricidad*numeroVagonesVigilancia);
			return fitness;
			
		}else{
		
		for(Integer numeroVecesVagon: cr.decode()){
			
			valor += numeroVecesVagon* ProblemaVagones.listaVagones.get(indice).getValor();
					
			peso += numeroVecesVagon* ProblemaVagones.listaVagones.get(indice).getPeso();
			
			indice++;
		
		}
		if(peso>ProblemaVagones.pesoTotal){
			fitness= -peso*(peso*peso);
		}else{
			fitness=valor;
		}
		return fitness;
		}}
	

	public SolucionVagones getSolucion(IndexChromosome cr) {
		Map<Vagon,Integer> map= new HashMap<Vagon,Integer>();
		
		List<Integer> ls = cr.decode();
		for (int i=0; i< ls.size();i++) {
			Vagon v = ProblemaVagones.listaVagones.get(i);
			map.put(v, ls.get(i));
		}
		return SolucionVagones.create(map);
	}
	
	
	public  Integer getMax(int index){
		return ProblemaVagones.listaVagones.get(index).getNumMaxDeUnidades();
	
	}
	public List<Integer> getNormalSequence() {
		return IntStream.range(0,getObjectsNumber())
				.boxed()
				.map(x->Lists2.nCopias(getMax(x),x).stream())
				.flatMap(x->x)
				.collect(Collectors.toList());
	}

}
