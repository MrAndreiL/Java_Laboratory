package com.example;

public abstract class Administrative {
    private final String name;

    public Administrative(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
