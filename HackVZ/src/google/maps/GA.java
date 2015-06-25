package google.maps;

import java.io.IOException;

public class GA {

	public static void main(String[] args) throws Exception {
		// Create initial population
		Population myPop = new Population(25, true);
		Algorithm alg = new Algorithm();
		
		// Evolve our population until we reach a near optimal solution
		int generations = 10;
		for (int i = 0; i < generations; i++) {
			System.out.println("Generation[" + i + "]");
			myPop = alg.evolvePopulation(myPop);
			System.out.println("Fitness: " + myPop.getFittest().getFitness());
		}
		
		Trip optimalTrip = myPop.getFittest();
		optimalTrip.toCSV();
		
		// Print final results
		System.out.println();
		System.out.println("Finalized!");
		System.out.println("Fitness Score: " + myPop.getFittest().getFitness());
		System.out.println("Solution: ");
		System.out.println(myPop.trips.toString());
	}

}
