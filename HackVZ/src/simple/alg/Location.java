package simple.alg;

public class Location {

	double longitude;
	double latitutde;
	String type;
	String name;
	String street;
	String city;
	String state;
	int zipCode;

	/*
	 * Constructor
	 */
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

	/*
	 * Getters
	 */
	// Checks if Location is a Verizon Location
	public boolean isVerizonLocation() {
		return this.type.equals("Verizon Locations");
	}

	// Checks if Location is a NBA stadium
	public boolean isNBAStadium() {
		return this.type.equals("NBA Stadium");
	}

	// Checks if Location is a Theme Park
	public boolean isThemePark() {
		return this.type.equals("Theme Park");
	}

	// Checks if Location is a NASA Center
	public boolean isNASACenter() {
		return this.type.equals("NASA Centers");
	}

	// Checks if Location is a Best Burger location
	public boolean isBestBurger() {
		return this.type.equals("Best Burgers");
	}

	// Checks if Location is a Hall of Fame location
	public boolean isHallOfFame() {
		return this.type.equals("Halls of Fame");
	}

	// Checks if Location is a Best Restaurants
	public boolean isBestRestaurants() {
		return this.type.equals("Best Restaurants");
	}
	
	public String getVisualDetails() {
		return "[" + name + ": " + street + ", " + city + ", " + state + "]";
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
