package ti3.vagones.ag;

import ti3.vagones.ProblemaVagones;
import ti3.vagones.SolucionVagones;
import us.lsi.ag.*;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.IndexChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.algoritmos.Algoritmos;

public class TestVagonesAG {

	// TODO
	public static void main(String[] args){
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS = 400;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 10;
		StoppingConditionFactory.FITNESS_MIN = 100.0;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;

		ProblemaVagones.create("Vagones.txt");
		ProblemaVagones.pesoTotal = 20;
		System.out.println("------");
		System.out.println("Peso total: " + ProblemaVagones.pesoTotal);
		System.out.println("------");
		System.out.println("Vagones:\n" + ProblemaVagones.listaVagones);
		System.out.println("------");
		System.out.println("Solución :");
		ProblemaVagonesAG p = new ProblemaVagonesAG();
		// TODO
		AlgoritmoAG a = Algoritmos.createAG(ChromosomeType.IndexRange, p);
		a.ejecuta();
		a.getBestFinal();
		IndexChromosome cromosoma= ChromosomeFactory.asIndex(a.getBestFinal());
		SolucionVagones v = p.getSolucion(cromosoma);
		 
		//System.out.println(a.getBestFinal());
		System.out.println("Mejor solución: "+ v);
		System.out.println("Fitness de la mejor solución: "+ a.getBestFinal().getFitness());
		System.out.println("------");
	}	

}
