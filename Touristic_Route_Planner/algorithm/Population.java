package algorithm;

import java.util.ArrayList;

/**
 * This class creates initial population if init is true. 
 * Generates random individuals which are added to the population.
 * 
 */

public class Population{
	//holds all the tours in a population
	Tour[] tours;
 
    //Creates initial population by generating new individuals(tours) and save them
    public Population(int popSize, boolean init) {
    	tours = new Tour[popSize];
        if (init) {
            for (int i = 0; i < popSize(); i++) {
                Tour initTour = new Tour();
                initTour.generateIndividual();
                saveTour(i, initTour);
            }
         }
    }
    
    //Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    
    
    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }
    
    //replace old tour by a new one
    public void replaceTour(Population pop,Tour newTour,Tour oldTour){
    	Tour tourObj = new Tour();
    	int tourIndex = tourObj.getTourIndex(pop, oldTour);
    	tours[tourIndex] = newTour;
  //  	System.out.println("Replaced:"+tourObj.getTourCities(tours[tourIndex]));
    //	System.out.println(tourIndex+" OO "+oldTour.getFitness()+" NN "+newTour.getFitness());
    }

   //checks if population contains a tour
    public boolean containsTour(Tour tour){
    	for(int p=0; p<this.popSize(); p++){
    		if(this.getTour(p).equals(tour)){
    			return true;
    		}
    	}
    	return false;
    }
    
    // Loops through the population, compares the fitness and finds the fittest tour.
    public Tour getFittest() {
    	Tour fittest = tours[0]; 
		double fit1 = 0;
		double fit2 = 0;
    	for (int i = 0; i < popSize(); i++) {
    	
    		fit1 =  Evaluator.getFitness(fittest);
    		fit2 = Evaluator.getFitness(getTour(i));
    		 if (fit1 < fit2) {
                 fittest = getTour(i);
             }
    		
        }
    //	  System.out.println("Fittest tour: "+getTourCities(fittest)+" Fitness:"+ fittest.getFitness());
        return fittest;
    }
    
   // Gets the worst fittest tour in the population 
    public Tour getWorstFittest() {
    	Tour wfittest = tours[0];    
        for (int i = 0; i < popSize(); i++) {
            if (Evaluator.getFitness(wfittest) > Evaluator.getFitness(getTour(i))) {
                wfittest = getTour(i);
            }
        }
        return wfittest;
    }

    // Gets population size
    public int popSize() {
        return tours.length;
    }
    
    public  ArrayList<Tour> seeAllIndividuals(){
    	ArrayList<Tour> tlist = new ArrayList<Tour>();
    	for(int t=0; t<tours.length; t++){
    		tlist.add(tours[t]);
    		}
		return tlist;
    	
    }
}