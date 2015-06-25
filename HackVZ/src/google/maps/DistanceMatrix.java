package google.maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DistanceMatrix {

	static String urlString = "";
	static String json = "";

	public DistanceMatrix(String url) {
		this.urlString = url;
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
