package google.maps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DistanceMatrix {
	public static final String APIKey = "AIzaSyCOUnvZXwXKQP1-b3cu-TU_RJFHjnb9vcU";

	static String urlString = "";
	static String json = "";

	Location location1;
	Location location2;

	public DistanceMatrix(Location loc1, Location loc2) {
		location1 = loc1;
		location2 = loc2;
		this.urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ location1.latitutde
				+ ","
				+ location1.longitude
				+ "&destinations="
				+ location2.latitutde
				+ ","
				+ location2.longitude
				+ "&units=imperial&language=en-EN&key="
				+ APIKey;
	}

	static void readUrl() throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			json = buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public double getDistance() {
		// Parse the JSON
		JsonParser jsonParser = new JsonParser();
		JsonObject distance = jsonParser.parse(json).getAsJsonObject()
				.getAsJsonArray("rows").get(0).getAsJsonObject()
				.getAsJsonArray("elements").get(0).getAsJsonObject()
				.getAsJsonObject().getAsJsonObject("distance");
		String distanceString = distance.get("text").getAsString();
		// System.out.println(distanceString);
		distanceString = distanceString.replaceAll(" mi", "");
		distanceString = distanceString.replaceAll(" ft", "");
		distanceString = distanceString.replaceAll(" in", "");
		distanceString = distanceString.replaceAll(",", "");
		return Double.parseDouble(distanceString);
	}

	public boolean queryDataExists() throws FileNotFoundException {
		boolean exists = false;
		Scanner scanner = new Scanner(new File("./src/QueryData.txt"));
		while (scanner.hasNextLine()) {
			String dataLine = scanner.nextLine();
			String[] data = dataLine.split(",");
			if (location1.name.equals(data[0])
					&& location2.name.equals(data[1]))
				exists = true;
		}
		return exists;
	}

	public void saveQueryData() throws IOException {
		File file = new File("./src/QueryData.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(location1.name + ",");
		bw.append(location2.name + ",");
		bw.write(this.getDistance() + "\n");
		bw.close();
	}

	public double retreiveQueryData() throws FileNotFoundException {
		String orginLocation = location1.name;
		String destinationLocation = location2.name;
		Scanner scanner = new Scanner(new File("./src/QueryData.txt"));
		while (scanner.hasNextLine()) {
			String dataLine = scanner.nextLine();
			String[] data = dataLine.split(",");
			if (location1.name.equals(data[0])
					&& location2.name.equals(data[1]))
				return Double.parseDouble(data[2]);
		}
		return 0.0;
	}

	static class Page {
		List<String> destination_addresses;
		List<String> origin_addresses;
		List<Row> rows;
		String status;
	}

	static class Row {
		List<Element> elements;
	}

	static class Element {
		List<Distance> distances;
		List<Duration> durations;
		String status;
	}

	static class Distance {
		String text;
		String value;
	}

	static class Duration {
		String text;
		String value;
	}

	public static void main(String[] args) throws Exception {

	}
}
