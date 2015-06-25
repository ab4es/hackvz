package google.maps;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DistanceMatrix {

	// public static final String APIKey =
	// "AIzaSyCaIReM8ShC5vxhKbL4ZdG8KoIoOeWDXlo";

	public static final String APIKey = "AIzaSyDkYvNNnW6zS3bk93XuCJHCcU9PLL_7FvA";

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
		distanceString = distanceString.replaceAll(",", "");
		return Double.parseDouble(distanceString);
	}

	public boolean queryDataExists() {
		boolean exists = false;
		
		return exists;
	}
	
	public void saveQueryData() throws IOException {
		FileWriter writer = new FileWriter("QueryData.txt");
		writer.append(location1.name + ",");
		writer.append(location2.name + ",");
		writer.append(this.getDistance() + "\n");
		writer.flush();
		writer.close();
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
