package google.maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DistanceMatrix {

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
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
		DistanceMatrix distMat = new DistanceMatrix();
		String json = distMat
				.readUrl("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&language=en-EN&key=AIzaSyCaIReM8ShC5vxhKbL4ZdG8KoIoOeWDXlo");

		JsonParser jsonParser = new JsonParser();
		JsonObject distance = jsonParser.parse(json)
				.getAsJsonObject().getAsJsonArray("rows").get(0)
				.getAsJsonObject().getAsJsonArray("elements").get(0)
				.getAsJsonObject().getAsJsonObject().getAsJsonObject("distance");
		System.out.println(distance.get("text").getAsString());

	}
}
