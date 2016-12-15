package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * A hashmap contains all edges (pair of cities) and time associated with them.
 * @author sadiatabassum
 *
 */
public class EdgeTimeMap {
	//private City c1;
	//private City c2;
	private Edge pair;
	private static double visitingTime;
	
	private static Map<Edge, Double> cityPairMap = new HashMap<Edge, Double>();
	
	public EdgeTimeMap(){
		
	}
	
	public EdgeTimeMap(Edge cpd, double visitingTime){
		this.pair = cpd;
		this.visitingTime = visitingTime;
		cityPairMap.put(cpd, visitingTime);
	}
	
	public static boolean containsPair(Edge cpd){
		if(cityPairMap.containsKey(cpd)){
			return true;
		}
		return false;
	}
	
	public static void replaceTime(Edge cpd,double time){
	//	CityMap cmp = new CityMap();
		cityPairMap.put(cpd, time);
	//	cmp.visitingTime = time;
	}
	
	 public static double getTimePairFromList(Edge cpd){
		 double time = 0;
		 if(cityPairMap.containsKey(cpd)){
			 time = cityPairMap.get(cpd);
		 }
	    	return time;
	 }
	 public static boolean isEmptyEdgeTimeMap(){
		 Iterator it = cityPairMap.entrySet().iterator();
		 while(it.hasNext()){
			 Object edge = it.next();
			 Double time = cityPairMap.get(edge);
			 if(time==null){
				 return true;
			 }
		 }
		 return false;
		 
	 }
}
