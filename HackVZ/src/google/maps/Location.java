package google.maps;

public class Location {

	double longitude;
	double latitutde;
	String type;
	String name;
	String street;
	String city;
	String state;
	int zipCode;

	public Location(double longitude, double latitude, String type,
			String name, String street, String city, String state, int zipCode) {
		this.longitude = longitude;
		this.latitutde = latitude;
		this.type = type;
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	@Override
	public String toString() {
		return "Location [longitude=" + longitude + ", latitutde=" + latitutde
				+ ", type=" + type + ", name=" + name + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + "]";
	}

	public static void main(String[] args) {

	}

}
