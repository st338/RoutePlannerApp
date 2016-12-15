package algorithm;

import gui.DetailsPanel;
import data.City;

/**
 * Genetic Algorithm class.
 * This class evolves a new population over one generation. Creates a new population
 * from the previous one. The main idea is to select 2 parent tours by Tournament selection, 
 * apply order one crossover and swap mutation. The
 * 
 */
public class GA {
	static int tournamentSize = 2;
	private static double mutationRate = 0.015;
	static Tour tourObj = new Tour();
	//private static boolean elitism;
    private static Tour genFit = new Tour();
    
	
    /**
     * Saves the fittest tour from the population at index 0 of new population
     * Applies parent selection, crossover, mutation on rest of the population (index 1 to pop size)
     * If the fitness of the new generation is better than previous generation, then save it for next generation.
     * Otherwise, replace worst-fittest in new generation with best-fittest in previous generation.
     * @param pop
     * @return
     */
	 public static Population evolvePopulation(Population pop) {
			
		    //Create new population 
			Population evolvedPop = new Population(pop.popSize(),false);
			
			//elitism: saves the fittest tour from the population
			evolvedPop.saveTour(0, pop.getFittest());
			//	System.out.println(Evaluator.getFitness(evolvedPop.getFittest()));
			//System.out.println(Tour.getTourCities(evolvedPop.getTour(0)));
			
			//get the saved fittest tour from previous generation
			genFit = Tour.getGenFittest();
			//	System.out.println("Gen:"+Tour.getTourCities(genFit));
			//	System.out.println(Tour.getTourCities(genFit));
			
			for(int i=1; i<evolvedPop.popSize(); i++){	
				Tour p1 = TournamentSelection(pop);
				Tour p2 = TournamentSelection(pop);
				Tour childTour = Crossover.orderOneCrossover(p1, p2);
				Tour mutateTour = Mutation.mutation(childTour);
				evolvedPop.saveTour(i, mutateTour);
			}

			//If new-gen fitness greater than prev-gen, save the new-gen fittest tour.
			if(Evaluator.getFitness(evolvedPop.getFittest()) >= Evaluator.getFitness(genFit)){
				Tour.saveGenFittest(evolvedPop.getFittest());
			}
			else{
				//Replace worst child with best parent
				if(Evaluator.getFitness(evolvedPop.getWorstFittest()) < Evaluator.getFitness(genFit)){
					evolvedPop.replaceTour(evolvedPop, genFit, evolvedPop.getWorstFittest());
				}
			}
			
			return evolvedPop;
	}
	
	//Selects fittest parent from 2 random tours
	private static Tour TournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false);
		   for (int i = 0; i < tournamentSize; i++) {
		        int randomId = (int) (Math.random() * pop.popSize()-1);
		        tournament.saveTour(i, pop.getTour(randomId));
	        }
		   Tour fittest = tournament.getFittest();
		   //  System.out.println("Fittest: "+fittest+" "+fittest.getFitness());
	       return fittest;
	}
	
	
}
