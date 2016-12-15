package algorithm;

import gui.DetailsPanel;

import java.util.*;

import data.City;

/**
 * Order one crossover between two parents.
 * Cities between two random positions from parent1 are copied to the child tour.
 * Cities that are not present in the child tour yet are copied to the child tour, 
 * keeping the same order they are in the parent2.
 * 
 **/
public class Crossover {
	
		private static Tour tourObj = new Tour();
		public static Tour orderOneCrossover(Tour parent1, Tour parent2){
		if(Math.random() <= DetailsPanel.getCrossover()){
			Tour offspring = new Tour();
			int length = parent1.tourSize();
			int pos1 = (int)(Math.random() * length); 
			int pos2 =  (int)(Math.random() * length); 
			while(pos1>= pos2) {
					pos1= (int)(Math.random() * length); 
					pos2= (int)(Math.random() * length); 
			}
				
			offspring.createInitialTour();
			//System.out.println(getTourCities(offspring));
			//add to child
			for(int i = pos1; i <= pos2; i++){
				offspring.setCity(i, parent1.getCity(i));
			}
		
			//holds parent1 elements that are not in child
			Tour y = new Tour();
			y.createInitialTour();
			int j = 0;
			for(int i = 0; i < length; i++){
				if(!arrayContains(offspring,parent1.getCity(i))){
					y.setCity(j, parent1.getCity(i));
					j++;
				}  
			}
			
		   Tour rotatedParent = new Tour();
		   ArrayList<City> pCity = new ArrayList<City>();
		   //l-pos2-1 defines which position to rotate
		   pCity.addAll(rotate(parent2,(length-pos2-1))); 
		   rotatedParent.setAllCities(pCity);
		   //  System.out.println(l-pos2-1);
		 
		   //For[0,1,2,3,4], if pos1=1 and pos2=3, total 3 elements are in the child.
		   //So 2 more elements remaining. Size of new array should be 5-3=2.
		   Tour y1 = new Tour();
		   y1.createInitialTour();
		
	/*		display(y1,"y1 : ");
			display(rotatedParent,"rotatedParent : ");
			display(y,"y : ");
			display(offspring,"Child : ");
			display(y1,"y1 : ");
			
	*/	
		  	int k = 0;
			for(int i = 0; i < rotatedParent.tourSize(); i++){
				if(arrayContains(y,rotatedParent.getCity(i))){
					y1.setCity(k,rotatedParent.getCity(i));
					k++;
				}
			}

	
		    int tourSize = length-(pos2-pos1)-1;
		    //	System.out.println(tourObj.getTourCities(offspring));
			for(int i = 0; i <tourSize; i++){
				int ci = (pos2+ i + 1) % length;// current index
				offspring.setCity(ci, y1.getCity(i)); 
				/*if(!arrayContains(offspring,null)){
					offspring.setCity(ci, y1.getCity(i)); 
					//display(offspring,"ol : ");
					System.out.println("OFF"+tourObj.getTourCities(offspring));
				}
				else{
					System.out.println("OFF2"+tourObj.getTourCities(offspring));
				}
				System.out.println("Ci "+ci);
				*/
			} 
		/*	System.out.println("Tour size: "+l);
			System.out.println("Range: "+pos1+" to "+pos2);
			System.out.println("parent1: "+tourObj.getTourCities(parent1));
			System.out.println("parent2: "+tourObj.getTourCities(parent2));
			System.out.println("child: "+tourObj.getTourCities(offspring));
			System.out.println();
			*/
		//	display(offspring,"Child : ");		
	
		return offspring;
	   }
	  else{
			return parent1;	 
	  }
	}
	
	//checks if an array contains a particular city
	public static boolean arrayContains(Tour arr, City e){
		for(int i = 0; i < arr.tourSize(); i++){
			if(arr.getCity(i)!=null){
				if(arr.getCity(i).equals(e)){
					//System.out.println(arr.getCity(i)+" "+e);
					return true;
				}
			}
			
		}
		return false;
	}
	
	//rotates the array from a given position
	private static ArrayList<City> rotate(Tour parent, int distance){
		ArrayList<City> parentCities = new ArrayList<City>();
		parentCities = tourObj.getTourCities(parent);
		Collections.rotate(parentCities, distance);
		return parentCities;
	}
	
	//test method for displaying tour
	public static void display(Tour tour, String name){
		int ll=tour.tourSize();
		for(int j1=0; j1<ll; j1++){
			System.out.println(name+" "+tour.getCity(j1));
		}
		System.out.println();
	 }

}

