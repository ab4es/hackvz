package google.maps;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Trip {

	Location[] locations;
	int endLocation;

	double fitness = 0.0;

	public Trip() throws NumberFormatException, IOException {
		CSV csv = new CSV();
		locations = csv.loadLocations();
		endLocation = locations.length - 1;
	}

	public void shuffleTrip() {
		List<Location> locationsList = Arrays.asList(locations);
		Collections.shuffle(locationsList);
		locations = locationsList.toArray(locations);
	}

	/*
	 * Getters and Setters
	 */
	// Get array of all locations
	public Location[] getLocations() {
		return locations;
	}

	// Set endLocation
	public void setEndLocation(int endLocation) {
		this.endLocation = endLocation;
	}

	// Get the fitness of the Trip
	public double getFitness() throws Exception {
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	// Get endLocation
	public int getEndLocation() {
		return endLocation;
	}

	// Get the number of locations in the Trip
	public int size() {
		return locations.length;
	}

	// Get the location of the Trip at an index
	public Location getLocation(int index) {
		return locations[index];
	}

	// Checks if the Trip contains a Location
	public boolean contains(Location location) {
		for (int i = 0; i < size(); i++) {
			if (this.getLocation(i) == location) {
				return true;
			}
		}
		return false;
	}

	// Set a Location at an index
	public void setLocation(int index, Location location) {
		locations[index] = location;
		fitness = 0;
	}

	public void toCSV() throws IOException {
		FileWriter writer = new FileWriter("./src/Team32.csv");
		writer.append("Name,\n");
		for (int i = 0; i < endLocation; i++)
			writer.append(locations[i].name + ",\n");
		writer.flush();
		writer.close();
	}

	public void printVisualDetailsHorizontal() {
		for (int i = 0; i < endLocation; i++)
			System.out.print(locations[i].getVisualDetails());
	}
	
	public void printVisualDetailsVertical() {
		for (int i = 0; i < endLocation; i++)
			System.out.println(locations[i].getVisualDetails());
	}

	public static void main(String[] args) throws Exception {
		Trip t = new Trip();
		t.shuffleTrip();
		FitnessCalc.getFitness(t);
	}
	

}
