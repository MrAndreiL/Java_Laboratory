package com.example;

public class Continent extends Administrative {
    private final int id;

    public Continent(int id, String name) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
