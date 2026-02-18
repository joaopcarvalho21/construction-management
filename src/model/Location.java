package model;

public class Location {

    private static int autoIncrement = 1;

    private final Integer id;
    private String street;
    private String district;
    private String city;

    public Location(String street, String district, String city) {
        this.id = autoIncrement++;
        this.street = street;
        this.district = district;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format(
            "Location [street=%s, district=%s, city=%s]",
            street, district, city
        );
    }
}