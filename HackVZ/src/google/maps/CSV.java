package google.maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

	private static final String fileName = "./src/locations.csv";
	File file = new File(fileName);

	public int getCSVLength() throws IOException {
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		br = new BufferedReader(new FileReader(file));
		int counter = 0;
		while ((line = br.readLine()) != null) {
			counter++;
		}

		return counter - 1;

	}

	public Location[] loadLocations() throws NumberFormatException, IOException {
		Location[] locations = new Location[this.getCSVLength()];
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		br = new BufferedReader(new FileReader(file));
		int counter = 0;
		while ((line = br.readLine()) != null) {
			String[] locationString = line.split(csvSplitBy);
			if (counter != 0) {
				double longitude = Double.parseDouble(locationString[0]);
				double latitude = Double.parseDouble(locationString[1]);
				String type = locationString[2];
				String name = locationString[3];
				String street = locationString[4];
				String city = locationString[5];
				String state = locationString[6];
				int zipCode = Integer.parseInt(locationString[7]);
				Location location = new Location(longitude, latitude, type,
						name, street, city, state, zipCode);
				locations[counter - 1] = location;
			}
			counter++;
		}
		return locations;
	}

	public static void main(String[] args) throws IOException {
		CSV csv = new CSV();
		csv.loadLocations();
	}

}
