package data;

import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * Constructs edge with two cities which is used when walking time needs to be assigned.
 * @author sadiatabassum
 *
 */
public class Edge {
	private static ArrayList<Edge> citypairs = new ArrayList<Edge>();
	private City c1;
	private City c2;
	
	public Edge(City c1, City c2){
		this.setFirstCity(c1);
		this.setSecondCity(c2);
	}
	
	public static void addEdge(Edge c){
		citypairs.add(c);
	}
	public ArrayList<City> getFirstColumn(){
		ArrayList<City> firstCol = new ArrayList();
		for(int j=0; j<citypairs.size(); j++){
			Edge temp = citypairs.get(j);
			firstCol.add(temp.getFirstCity());
		}
		return firstCol;
	}
	
	public ArrayList<City> getSecondColumn(){
		ArrayList<City> secCol = new ArrayList();
		for(int j=0; j<citypairs.size(); j++){
			Edge temp = citypairs.get(j);
			secCol.add(temp.getSecondCity());
		}
		return secCol;
	}
	
	public City getFirstCity() {
		return this.c1;
	}
	public void setFirstCity(City c1) {
		this.c1 = c1;
	}
	public City getSecondCity() {
		return c2;
	}
	public void setSecondCity(City c2) {
		this.c2 = c2;
	}
	
	public static int getSize(){
		return citypairs.size();
	}
	
	public static void removeAll(){
		citypairs.removeAll(citypairs);
	}
	
	 public static Edge getEdgeFromList(City c1, City c2){
		 for(Edge a: citypairs){
			 if(a.c1.equals(c1) && a.c2.equals(c2)){
				 return a;
			 }
		 }
		return null;
	 }
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(c1).
            append(c2).
            toHashCode();
    }
	 @Override
	    public boolean equals(Object obj) {
	       if (!(obj instanceof Edge))
	            return false;
	        if (obj == this)
	            return true;

	        Edge rhs = (Edge) obj;
	        return new EqualsBuilder().
	            // if deriving: appendSuper(super.equals(obj)).
	            append(c1, rhs.c1).
	            append(c2, rhs.c2).
	            isEquals();
	    }
}
