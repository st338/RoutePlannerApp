package data;

import java.util.ArrayList;
/*
 * This class deals with everything related to a city such as 
 * constructing a city from x and y co-ordinates, methods to get any information 
 * of a city and distance between two cities etc.
 * 
 */
import java.util.Iterator;
/**
 * Creates city.Contains method that stores user selected cities in an arraylist.
 * Preference to city can be added.
 * @author sadiatabassum
 *
 */
public class City {
	 int x;
	 int y;
	 String name;	
	 double pref;
	 private static Double preference=null;
	 private static ArrayList<City> cityList = new ArrayList<City>();
	 
	    // Constructuctor
	    public City(){
	        //this.x = (int)(Math.random()*200);
	       // this.y = (int)(Math.random()*200);
	    }
	    
	    // Constructs a city from a given name
	    public City(String string) {
	    	this.name = string;
		}
	    
	    // Constructs a city at chosen x, y location with pref
	    public City(String name, int x, int y, double pref){
	        this.x = x;
	        this.y = y;
	        this.name = name;
	        this.pref = pref;
	    }
	   

		public String getName(){
	    	return this.name;
	    }
	    
	    // Gets city's x coordinate
	    public int getX(){
	        return this.x;
	    }
	    
	    // Gets city's y coordinate
	    public int getY(){
	        return this.y;
	    }
	    
	    // Gets city's y preference
	    public double getPref(){
	        return this.pref;
	    }
	    
	    //gets a city
	    public static City getCityFromCityList(int index){
	    	return cityList.get(index);
	    }
	    
	    //adds a city
	    public static void addCityToCityList(City city){
	    	cityList.add(city);
	    }
	    
	    //get number of cities
	    public static int getTotalCities(){
	    	return cityList.size();
	    }
	    
	    //sets preference of a city
	    public static void setPreference(double preference, City city){
	    	city.pref = preference;
	    }
	 
	    //get preference of a city
	    public static double getPreference(City city){
	    	return city.pref;
	    }
	    
	    
	    // Gets the distance to given city
	    public double distanceTo(City city){
	        int xDistance = Math.abs(getX() - city.getX());
	        int yDistance = Math.abs(getY() - city.getY());
	        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
	        return distance;
	    }
	    
	    
	    public static boolean containsCity(City c){
	    	if(cityList.contains(c)){
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    
	    public static void removeAllCity(){
	    	cityList.removeAll(cityList);
	    }
	    public static ArrayList<City> getAllCities(){
	    	return cityList;
	    }
	    
	    public static City getCityFromName(String cname){
	    	for(City c : cityList ){
	    		if(c.name.equals(cname)){
	    			return c;
	    		}
	    	}
	    	return null;
	    }
	    @Override
	    public String toString(){
	       // return getX()+", "+getY();
	    	return getName();
	    }
	    
	    //checks if any city doesn't have preference score
	    public static boolean isEmptyPrefTable(){
			for(int k=0; k<cityList.size(); k++){
				preference = City.getPreference(cityList.get(k));
				if(preference==0){
					return true;
				}
			}
			return false; 
		 }
}
