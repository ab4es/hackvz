package google.maps;

import java.util.ArrayList;

public class FitnessCalc {

	// Calculate a Trip's fitness
	static double getFitness(Trip trip) throws Exception {
		// Initialize variables needed to calculate fitness
		double fitness = 0;
		double totalDuration = 0;
		int counter = 0;
		ArrayList<String> statesVisited = new ArrayList<String>();

		// Loop through all locations within the Trip or until travel time
		// exceeds
		// 224 hours
		while (totalDuration < 224 && counter < trip.size() - 1) {
			int location1Fitness = 0;

			Location location1 = trip.getLocation(counter);
			Location location2 = trip.getLocation(counter + 1);

			double distance = getDistance(location1, location2);
			double l2lDuration = calculateDuration(distance);

			// Ensure next location will not cause for total travel time to
			// surpass 224 hours
			if (totalDuration + l2lDuration > 224)
				break;
			totalDuration += l2lDuration;

			// l2lDuration points
			if (l2lDuration <= 1) {
				location1Fitness += 20;
			} else if (l2lDuration > 1 && l2lDuration <= 2.5) {
				location1Fitness += 15;
			} else if (l2lDuration > 2.5 && l2lDuration <= 4) {
				location1Fitness += 10;
			} else if (l2lDuration > 4 && l2lDuration <= 5.5) {
				location1Fitness += 5;
			} else if (l2lDuration > 5.5 && l2lDuration <= 7) {
				location1Fitness += 2.5;
			}

			// Location type points
			if (location1.isVerizonLocation()) {
				location1Fitness += 10;
			} else if (location1.isNBAStadium()) {
				location1Fitness += 8;
			} else if (location1.isThemePark()) {
				location1Fitness += 5;
			} else if (location1.isNASACenter()) {
				location1Fitness += 5;
			} else if (location1.isBestBurger()) {
				location1Fitness += 5;
			} else if (location1.isHallOfFame()) {
				location1Fitness += 5;
			} else if (location1.isBestRestaurants()) {
				location1Fitness += 2;
			}

			// Keep ArrayList of all states visited on this Trip
			String location1State = location1.state;
			if (!statesVisited.contains(location1State)) {
				statesVisited.add(location1State);
			}

			fitness += location1Fitness;
			counter++;
		}

		// Store the index of the last location traveled to
		trip.setEndLocation(counter - 1);

		// States visited points
		int numberOfStates = statesVisited.size();
		fitness += numberOfStates * 3;

		// Bonus points
		if (numberOfStates >= 5 && numberOfStates <= 9)
			fitness += 15;
		else if (numberOfStates >= 10 && numberOfStates <= 19)
			fitness += 25;
		else if (numberOfStates >= 20)
			fitness += 50;

		return fitness;
	}

	// If needed, query the Google DistanceMatrix API to determine the distance
	// between two locations
	public static double getDistance(Location location1, Location location2)
			throws Exception {
		double distance = 0;

		DistanceMatrix matrix = new DistanceMatrix(location1, location2);

		// If the query data doesn't exist, query the data and save it to an
		// existing text file
		if (!matrix.queryDataExists()) {
			matrix.readUrl();
			matrix.saveQueryData();
			distance = matrix.getDistance();
		}
		// If the query data exists, find the query data that applies
		else {
			distance = matrix.retreiveQueryData();
		}

		return distance;
	}

	// Calculate the travel duration and time stayed (1 hour)
	public static double calculateDuration(double distance) {
		return (1 + distance / 65);
	}

	public static void main(String[] args) throws Exception {

	}

}
