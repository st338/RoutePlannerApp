package algorithm;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import data.City;
import data.Edge;
import data.EdgeTimeMap;
/**
 * This class calls a method from GA class to evolve a population, evaluates population and
 * finds the fittest tour. It constructs the output string with fittest tour, fitness value of the fittest tour, time
 * and preference score.
 * 
 * @author sadiatabassum
 *
 */
public class RunGA extends JPanel{
		private Tour tourObj = new Tour();
		private String result = null;
		double fitness = 0;
		double score = 0;
		double currentTime = 0;
		private static Tour newTour;
		public RunGA(){
	
		}
		
		public String runAlg(Population pop){
			 pop = GA.evolvePopulation(pop);

	         Tour fittest = pop.getFittest();
	 	     Tour valTour = tourObj.getValidTour(fittest);
     		
	 	     fitness = Evaluator.getFitness(fittest);
     		
	         DecimalFormat df = new DecimalFormat("#.###"); 
	         df.setRoundingMode(RoundingMode.CEILING); 
	         
	         score = Tour.getTourPrefSum(valTour);
	         // double score = Tour.getScoreFuncValue(fittest);
	         
	         result = ""+tourObj.getTourCities(valTour)+" Fitness:"+df.format(fitness)+" Totaltime:"+valTour.getTotalVisitTime()+" Score: "+score;
     		
     		return result;
		}	
	
		public String displayTour(){
			return result;
		}

}
