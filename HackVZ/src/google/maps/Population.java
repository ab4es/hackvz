package google.maps;

import java.io.File;
import java.io.IOException;

public class Population {

	Location[] locations;

	/*
	 * Constructors
	 */
	// Create the initial population
	public Population() throws NumberFormatException, IOException {
		// Intialize population
		CSV csv = new CSV();
		locations = csv.loadLocations();
	}

	// Create custom population
	public Population(int populationSize) throws NumberFormatException,
			IOException {

		locations = new Location[populationSize];
	}

	public void print() {
		for (Location l: locations)
			System.out.println(l.toString());
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Population pop = new Population();
		pop.print();
	}

}
