package algorithm;

import gui.DetailsPanel;

import java.util.ArrayList;
import java.util.Collections;

import data.City;
import data.EdgeTimeMap;
import data.Edge;
/**
 * Constructs a individuals containing a number of cities.
 * Random permutations are turned into valid solutions using decoder.
 * @author sadiatabassum
 *
 */
public class Tour{
	
//	static double timeLimit = 25; // 20;
	
	private double totalTourTime = 0;
	
	
	//private int distance = 0;
	//private Tour permutation;
    private static double timeLimit = 0;
	private ArrayList<City> tour = new ArrayList<City>();
    private ArrayList<City> cityArray = new ArrayList<City>();
    private static Tour fittestTour = new Tour();
  
    // Constructs a blank tour
    public Tour(){
    	
    }
    
    public Tour(ArrayList<City> tour){
        this.tour = tour;
    }
    
    public Tour(City c1, City c2){
        this.tour.add(c1);
        this.tour.add(c2);
    }
    
    //generates individuals/valid solutions from random permutations of given cities using decoder
    public void generateIndividual() {
    	City tempCity = new City();
        for (int cityIndex = 0; cityIndex < City.getTotalCities(); cityIndex++) {
        	tempCity = City.getCityFromCityList(cityIndex);
        	cityArray.add(tempCity);
        }
        randomPermutation(cityArray);
        //  System.out.println("Before: "+cityArray);
        decoder(cityArray);
        //    ArrayList<City> t = decoder(cityArray);
        //   System.out.println("Valid tour: "+t);
        // System.out.println();
    } 
    
    //shuffles array elements for a random permutations
	private void randomPermutation(ArrayList<City> arrayOfCities) {
	     Collections.shuffle(cityArray);
	     tour.addAll(cityArray);
	}

	//creates valid tour by adding cities that satisfies the time limit.
	public static ArrayList<City> decoder(ArrayList<City> permutation){
			timeLimit = DetailsPanel.getTimeLimit();
		   ArrayList<City> validTour = new ArrayList<City>();
			double tempTime = 0;	
			int count = 0;
			City prevCity = null;
			for(City c: permutation){
				City currentCity = c;
				//adds first city in the tour
				if(count==0){
					validTour.add(c);
					count++;
				}
				else{
					//compare visiting time of each city with the previous one 
					if(count>0){
						prevCity = validTour.get(count-1);
						Edge cpd = Edge.getEdgeFromList(prevCity,currentCity);
						tempTime += EdgeTimeMap.getTimePairFromList(cpd);
					/*	CityPair ctp = CityPair.getPairFromList(prevCity,currentCity);
						//tempDistance += prevCity.distanceTo(currentCity);
						tempDistance += CityPair.getTimePairFromList(ctp);
						*/
						
						count++;
						//System.out.println(tempDistance);
					}
					if(tempTime<=timeLimit){
						validTour.add(currentCity);
						
					}
					else{
						break;
					}
				
				}

			}

			//System.out.println("Valid:"+validTour);
			return validTour;
	}

    //Gets the valid tour from original tour/permutation.
    public Tour getValidTour(Tour tour){
    	ArrayList<City> tourCityList = new ArrayList<City>();
    	tourCityList = decoder(getTourCities(tour));
    	Tour valid = new Tour();
    	valid.addCities(tourCityList);
    	return valid;
    }
    
 	// Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }
    
    //Adds cities in the tour
    public void addCities(ArrayList<City> arr){
		for(int a=0; a<arr.size(); a++){
			tour.add(arr.get(a));
		}
	}
    
	// Gets a city from the tour
    public static ArrayList<City> getTourCities(Tour selectedTour) {
    	ArrayList<City> tourCities = new ArrayList<City>();
    	for(int tourIndex=0; tourIndex<selectedTour.tourSize(); tourIndex++){
    		tourCities.add(selectedTour.getCity(tourIndex));
    	}
        return tourCities;
    }
    
   /* public ArrayList<City> getRangeOfCity(Tour selectedTour, int tourPosition1,int tourPosition2) {
    	ArrayList<City> tourCities = new ArrayList<City>();
    	for(int tourIndex=tourPosition1; tourIndex<tourPosition2; tourIndex++){
    		tourCities.add(selectedTour.getCity(tourIndex));
    	}
    	  return tourCities;
    }*/
    
    // Sets a city in a certain position within a tour
    public void setCity(int cityPosition, City ci) {
    	tour.set(cityPosition, ci);
      //  fitness = 0;
      //  distance = 0;
    }
    
    //Set all cities in a tour
    public void setAllCities(ArrayList<City> cityArray){
    	createInitialTour();
    	int cIndex = 0;
    	for(City c: cityArray){
    		tour.set(cIndex, c);
    		cIndex++;
    	}
    }
    
    public void createInitialTour(){
    	for(int c=0; c<City.getTotalCities(); c++){
    		tour.add(null);
    	}  	
    }
    
    // Check if the tour contains a city
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    //Gets the total visiting time of a tour.
    public double getTotalVisitTime(){
    	//double totalTourTime = 0;
    			for(int cityIndex=0; cityIndex<tour.size(); cityIndex++){
    				City fromCity = getCity(cityIndex);
    				City toCity = null;
    				if(cityIndex+1<tour.size()){
    					toCity = getCity(cityIndex+1);
    					if(toCity != null){
    						Edge cpd = Edge.getEdgeFromList(fromCity, toCity);
    						totalTourTime += EdgeTimeMap.getTimePairFromList(cpd);
    						/*
    						 * CityPair cv = CityPair.getPairFromList(fromCity, toCity);
    						 * totalTourTime += CityPair.getTimePairFromList(cv);
    						*/
    					//	System.out.println(fromCity+" "+toCity+" "+CityPair.getTimePairFromList(cv));
    					}
 	    		}
    		
    		}
    		//System.out.println(totalTourTime);
    	
		return totalTourTime;
    }
    
    // Gets tour size
    public int tourSize() {
        return tour.size();
    }
    
    // Gets index of a tour in a population
    public static int getTourIndex(Population pop, Tour tour) {
    	int tourIndex = 0;
    	for(int p=0; p<pop.popSize(); p++){
    		if(pop.getTour(p).equals(tour)){
    			tourIndex = p;
    		}
    	}
        return tourIndex;
    }
    
    public static double getTourPrefSum(Tour t){
    	int prfScore = 0;
    	for(int j=0; j<t.tourSize(); j++){
    		City ct = t.getCity(j);
    //		System.out.println("pp"+City.getCityFromCityList(j)+" "+ ct.getPref());
    		prfScore += ct.getPref();
    	}
    	return prfScore;
    }
    
    public static void saveGenFittest(Tour fitTour){
    	ArrayList<City> tourCityList2 = getTourCities(fitTour);
    	fittestTour = new Tour(tourCityList2);
    	
    }
    
    public static Tour getGenFittest(){
    	return fittestTour;
    }
    
    public static double getScoreFuncValue(Tour tour){
    	double score = Tour.getTourPrefSum(tour)/((tour.tourSize())*10);
    	return score;
    }
    
    public void setTimeLimit(double time){
    	timeLimit = time;
    }
    
//  //Gets the total distance of a tour
//  public int getTourDistance(){
//  //	if (distance == 0) {
//  		int totalTourDistance = 0;
//  			for(int cityIndex=0; cityIndex<tour.size(); cityIndex++){
//  				City fromCity = getCity(cityIndex);
//  				City toCity = new City();
//  				if(cityIndex+1<tour.size()){
//  					toCity = getCity(cityIndex+1);
//  					if(toCity != null){
//  						totalTourDistance += fromCity.distanceTo(toCity);
//      				}
//	    			}
//	    		}
//  		//	distance = totalTourDistance;
//  		//}
//  	return totalTourDistance;
//  }
    
 }
