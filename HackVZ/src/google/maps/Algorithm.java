package google.maps;

import java.io.IOException;

public class Algorithm {

	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 3;
	private static final boolean elitism = true;

	public static Population evolvePopulation(Population pop) throws Exception {
		Population newPopulation = new Population(pop.size(), false);

		// Keep the best individual
		if (elitism) {
			newPopulation.saveTrip(0, pop.getFittest());
		}

		// Breeding population
		int elitismOffset;
		if (elitism) {
			elitismOffset = 1;
		} else {
			elitismOffset = 0;
		}

		// Loop through the population and 'breed' trips
		for (int i = elitismOffset; i < pop.size(); i++) {
			Trip trip1 = tournamentSelection(pop);
			Trip trip2 = tournamentSelection(pop);
			Trip newTrip = breed(trip1, trip2);
			newPopulation.saveTrip(i, newTrip);
		}

		// Mutate the population
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getTrip(i));
		}

		return newPopulation;
	}

	public static Trip breed(Trip trip1, Trip trip2)
			throws NumberFormatException, IOException {
		// Create a new trip that will be the result of breeding the two trips
		Trip newTrip = new Trip();

		// Get a random start and end location from trip1
		int startIndex = (int) (Math.random() * trip1.size());
		int endIndex = (int) (Math.random() * trip1.size());

		// Loop and add the sub trip from trip1 to the new trip
		for (int i = 0; i < newTrip.size(); i++) {
			// If the startIndex is less the endIndex
			if (startIndex < endIndex && i > startIndex && i < endIndex) {
				newTrip.setLocation(i, trip1.getLocation(i));
			} else if (startIndex > endIndex) {
				if (!(i < startIndex && i > endIndex)) {
					newTrip.setLocation(i, trip1.getLocation(i));
				}
			}
		}

		// Loop through trip2's locations
		for (int i = 0; i < trip2.size(); i++) {
			// If the new trip doesn't have the city, add it
			if (!newTrip.contains(trip2.getLocation(i))) {
				// Loop to find a spare position in the new Trip
				for (int ii = 0; ii < newTrip.size(); ii++) {
					// Spare position found, add the location
					if (newTrip.getLocation(ii) == null) {
						newTrip.setLocation(ii, trip2.getLocation(i));
						break;
					}
				}
			}
		}

		return newTrip;
	}

	// Mutate a trip
	public static void mutate(Trip trip) {
		// Loop through the locations
		for (int locationPos1 = 0; locationPos1 < trip.size(); locationPos1++) {
			if (Math.random() <= mutationRate) {
				// Pick random position in the array of locations
				int locationPos2 = (int) (trip.size() * Math.random());

				// Get the locations at the respective places in the trip
				Location location1 = trip.getLocation(locationPos1);
				Location location2 = trip.getLocation(locationPos2);

				trip.setLocation(locationPos2, location1);
				trip.setLocation(locationPos1, location2);
			}
		}
	}

	// Select trips for breeding
	private static Trip tournamentSelection(Population pop) throws Exception {
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);
		// For each place in the tournament get a random trip
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.size());
			tournament.saveTrip(i, pop.getTrip(randomId));
		}

		// Get the fittest trip
		Trip fittest = tournament.getFittest();
		return fittest;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Algorithm alg = new Algorithm();
		Trip t = new Trip();
		System.out.println("Original trip: ");
		System.out.println(t.toString());
		alg.mutate(t);
		System.out.println("Mutated trip: ");
		System.out.println(t.toString());

	}

}
