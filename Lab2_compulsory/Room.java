
package com.company;

public class Room {
    /* Data members */
    private String name;
    private int capacity;
    private RoomType type;

    /* Public constructors. */
    public Room(String name, int capacity, RoomType type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    /* Getters and setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomType getType() {
        return type;
    }

    /* toString override */
    @java.lang.Override
    public java.lang.String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type=" + type +
                '}';
    }
}
