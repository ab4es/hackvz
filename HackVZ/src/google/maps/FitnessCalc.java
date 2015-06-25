package google.maps;

import java.util.ArrayList;

public class FitnessCalc {

	// Calculate individuals fitness by comparing it to our candidate solution
	static double getFitness(Trip trip) throws Exception {
		double fitness = 0;
		double duration = 0;
		int counter = 0;
		double totalDistance = 0.0;
		ArrayList<String> statesVisited = new ArrayList<String>();

		while (duration < 224 && counter < trip.size() - 1) {
			int location1Fitness = 0;
			double travelDuration = 0;
			Location location1 = trip.getLocation(counter);
			Location location2 = trip.getLocation(counter + 1);

			double distance = getDistance(location1, location2);
			totalDistance += distance;
			travelDuration = calculateDuration(distance);
			if (duration + travelDuration > 224)
				break;

			duration += travelDuration;
			// System.out.println("Duration: " + duration);

			// System.out.println(location1.toString());

			if (location1.isVerizonLocation()) {
				location1Fitness += 10;
				// System.out.println("is Verizon location");
			} else if (location1.isNBAStadium()) {
				location1Fitness += 8;
				// System.out.println("is NBA Stadium");
			} else if (location1.isThemePark()) {
				location1Fitness += 5;
				// System.out.println("is Theme Park");
			} else if (location1.isNASACenter()) {
				location1Fitness += 5;
				// System.out.println("is NASA Center");
			} else if (location1.isBestBurger()) {
				location1Fitness += 5;
				// System.out.println("is Best Burger");
			} else if (location1.isHallOfFame()) {
				location1Fitness += 5;
				// System.out.println("is Hall of Fame");
			} else if (location1.isBestRestaurants()) {
				location1Fitness += 2;
				// System.out.println("is Best Restaurants");
			}

			String location1State = location1.state;
			if (!statesVisited.contains(location1State)) {
				statesVisited.add(location1State);
				// System.out.println(location1State + " added");
			}

			double distanceBoost = 7500 / totalDistance;
			if (distanceBoost >= 15)
				distanceBoost = 15;

			fitness += distanceBoost;
			fitness += location1Fitness;
			counter++;
		}

		int numberOfStates = statesVisited.size();
		fitness += numberOfStates * 3;
		if (numberOfStates >= 5 && numberOfStates <= 9)
			fitness += 15;
		else if (numberOfStates >= 10 && numberOfStates <= 19)
			fitness += 25;
		else if (numberOfStates >= 20)
			fitness += 50;
		// System.out.println("TOTAL STATES VISITED: " + numberOfStates);

		trip.setEndLocation(counter - 1);

		// System.out.println("FITNESS: " + fitness);
		// System.out.println("===============");
		// System.out.println();
		return fitness;
	}

	public static double getDistance(Location location1, Location location2)
			throws Exception {
		double distance = 0;

		DistanceMatrix matrix = new DistanceMatrix(location1, location2);

		// If the query data doesn't exist, query the data and save it
		if (!matrix.queryDataExists()) {
			matrix.readUrl();
			matrix.saveQueryData();
			distance = matrix.getDistance();
		}
		// If the query data exists, read the query data, don't make a new query
		else {
			// System.out.println("pulling from data...");
			distance = matrix.retreiveQueryData();
		}

		return distance;
	}

	// Duration
	public static double calculateDuration(double distance) {
		return (1 + distance / 65);
	}

	public static void main(String[] args) throws Exception {
		FitnessCalc calc = new FitnessCalc();
		Trip t = new Trip();
		Location loc1 = t.getLocation(0);
		Location loc2 = t.getLocation(1);
		getDistance(loc1, loc2);
	}

}
