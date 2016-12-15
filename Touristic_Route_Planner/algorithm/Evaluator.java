package algorithm;

import gui.DetailsPanel;

import java.util.ArrayList;

import data.City;
/**
 * THIS CLASS CONTAINS THE METHOD FOR CALCULATING THE FITNESS OF A SOLUTION 
 */
public class Evaluator {
	private static double totalPrfScore = 0;
	private static double paramAlpha = 0.5;
	private static double paramBeta = 0.5;
	private static double fitness = 0;
	private static double f1 = 0, f2=0, f3=0, fitness1=0, fitness2=0;
	public Evaluator(){
		
	}

    // Contains fitness function. Gets the fitness of a solution.
    public static double getFitness(Tour myTour) {
       
    	ArrayList<City> tourCityList = Tour.getTourCities(myTour);
    	Tour validT = new Tour();
    	validT.addCities(Tour.decoder(tourCityList));
    	//System.out.println("Cities in the valid tour: "+Tour.getTourCities(validT));
    	
    	double validTime = validT.getTotalVisitTime(); 
    	//System.out.println("validTime: "+validTime);
    	
    	//tour size of valid tour
    	int ts = validT.tourSize();  
    	//System.out.println("toursize: "+ts+" total cities: "+tt);
    	
    	// Get sum of preference-scores of valid tour 
    	totalPrfScore = Tour.getTourPrefSum(validT);   	
    	//Get parameters alpha and beta from gui
    	paramAlpha = DetailsPanel.getAlpha();
    	paramBeta = DetailsPanel.getBeta();
   	
    	/**
    	 * FITNESS FUNCTION 
    	 */
    	
    	fitness1 = Math.pow(Math.log10(ts),(paramBeta+0.01))/Math.pow((Math.log10(validTime+1)+1),(1+0.01-paramBeta));
    
    	f1 = Math.pow(Math.log10(totalPrfScore),paramAlpha);
    	f2 = Math.pow(Math.log10(ts),(paramBeta+0.01));
    	f3 = Math.pow((Math.log10(validTime+1)+1),(1+0.01-paramAlpha-paramBeta));
    	fitness2 = (f1*f2)/f3;
    	
    	if(DetailsPanel.getSelectedButtonText().equals("No")){
    		fitness = fitness1; 
    	}
    	else{
    		fitness = fitness2; 
    	}
    	//System.out.println("Fitness: "+fitness); 
        return fitness;
    }
    
 
    
}
