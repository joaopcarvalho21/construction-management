package model;

public class Location {

    private static int autoIncrement = 1;
    private final Integer id;
    private String address;
    private String city;

    public Location(String address, String city) {
    	if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        this.id = autoIncrement++;
        this.address = address;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format(
            "Location [id=%d, address=%s, city=%s]",
            id, address, city
        );
    }
}
