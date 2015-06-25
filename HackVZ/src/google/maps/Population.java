package google.maps;

import java.io.File;
import java.io.IOException;

public class Population {

	Trip[] trips;

	/*
	 * Constructors
	 */
	// Create the initial population
	public Population(int populationSize, boolean initialize)
			throws NumberFormatException, IOException {
		trips = new Trip[populationSize];
		// Initialize population
		if (initialize) {
			for (int i = 0; i < this.size(); i++) {
				Trip temp = new Trip();
				temp.shuffleTrip();
				saveTrip(i, temp);
			}
		}
	}
	
	// Get the fittest trip
	public Trip getFittest() throws Exception {
		Trip fittest = trips[0];
		for (Trip t: trips) {
			if (fittest.getFitness() <= t.getFitness()) {
				fittest = t;
			}
		}
		return fittest;
	}

	public void saveTrip(int index, Trip trip) {
		trips[index] = trip;
	}

	public void print() {
		for (Trip t : trips) {
			System.out.println(t.toString());
			System.out.println("==========================");
			System.out.println();
		}
	}
	
	public int size() {
		return trips.length;
	}
	
	public Trip getTrip(int index) {
		return trips[index];
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Population pop = new Population(2, true);
		pop.print();
	}

}
