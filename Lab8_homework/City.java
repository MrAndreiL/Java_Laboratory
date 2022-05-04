package com.example;

public class City extends Administrative {

    /* Model the city database table */
    private final int id;
    private final String country;
    private final String capital;
    private final double longitude;
    private final double latitude;

    public City(int id, String country, String name, String capital, double latitude, double longitude) {
        super(name);
        this.id = id;
        this.capital = capital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
