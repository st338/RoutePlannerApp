package algorithm;

import gui.DetailsPanel;
import data.City;
/** 
 * Mutation rate decides the probability of applying mutation.
 * Swaps cities between two random positions. 
 */
public class Mutation {
	
	public static Tour mutation(Tour tour) {
		double mutationRate = DetailsPanel.getMutation();
		//	System.out.println(mutationRate);
		
		 if(Math.random() < mutationRate){
			int tourPos1 =  (int) (tour.tourSize() * Math.random());
		    int tourPos2 = (int) (tour.tourSize() * Math.random());
		    while(tourPos1==tourPos2){
		    	tourPos2 = (int) (tour.tourSize() * Math.random());
		    }
		    City city1 = tour.getCity(tourPos1);
	        City city2 = tour.getCity(tourPos2);
	        //  System.out.println("Before mutation: "+tourObj.getTourCities(tour));
	        tour.setCity(tourPos1, city2);
	        tour.setCity(tourPos2, city1);
	        //System.out.println("After mutation: "+tourObj.getTourCities(tour));
		 }
		 return tour;
     }
}
