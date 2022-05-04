package com.example;

public class Country extends Administrative {

    /* Model the country database table */
    private final int id;
    private final int code;
    private final String continent;

    public Country(int id, String name, int code, String continent) {
        super(name);
        this.id = id;
        this.code = code;
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getContinent() {
        return continent;
    }
}
