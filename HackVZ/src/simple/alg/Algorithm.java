package simple.alg;

import google.maps.Population;
import google.maps.Trip;

import java.io.IOException;

public class Algorithm {

	public Trip getBestTrip(Population pop) throws NumberFormatException, IOException {
		Trip best = new Trip();
		
		// Loop through all trips
		for (int i = 0; i < pop.size(); i++) {
			
		}
	}

	public static void main(String[] args) throws Exception {
		// Create initial population
		Population myPop = new Population(25, true);
		Algorithm alg = new Algorithm();

		// Evolve our population until we reach a near optimal solution
		int generations = 5;
		for (int i = 0; i < generations; i++) {
			System.out.println("Generation[" + i + "]");
			System.out.println("Creating population...");
			myPop = alg.evolvePopulation(myPop);
			System.out.println("Most fit Trip:");
			myPop.getFittest().printVisualDetailsVertical();
			System.out.println("Fitness: " + myPop.getFittest().getFitness());
			System.out.println();
			Trip optimalTrip = myPop.getFittest();
			optimalTrip.toCSV();
		}

		// Print final results
		System.out.println();
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println("Finalized!");
		System.out.println("Fitness Score: " + myPop.getFittest().getFitness());
		System.out.println("Optimal Solution:");
		System.out.println("=================");
		myPop.getFittest().printVisualDetailsVertical();
	}

}
