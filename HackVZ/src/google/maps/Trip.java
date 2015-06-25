package google.maps;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import simpleGa.FitnessCalc;

public class Trip {

	Location[] locations;
	
	private int fitness = 0;

	public Trip() throws NumberFormatException, IOException {
		CSV csv = new CSV();
		locations = csv.loadLocations();
	}

	public void shuffleTrip() {
		List<Location> locationsList = Arrays.asList(locations);
		Collections.shuffle(locationsList);
		locations = locationsList.toArray(locations);
		for (int i = 0; i < locations.length - 1; i++) {
			System.out.println(i + ": " + locations[i].toString());
		}
	}
	
	public Location[] getLocations() {
		return locations;
	}
	
	public void setLocation(int index, Location location) {
		locations[index] = location;
		fitness = 0;
	}
	
	public int getFitness() {
		
		// TO DO
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	@Override
	public String toString() {
		String res = "";
		for (Location l : locations) {
			res += l.toString();
		}
		return res;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Trip t = new Trip();
		t.shuffleTrip();
		// System.out.println(t);
	}

}
