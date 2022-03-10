package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public class Lab extends Room{
    private String operatingSystem;

    Lab (String name, int capacity, String operatingSystem) {
        super(name, capacity);
        this.operatingSystem = operatingSystem;
    }

    public String getOS() {
        return operatingSystem;
    }

    public void setProjector(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
