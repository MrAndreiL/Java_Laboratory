package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public abstract class Room {
    private String name;
    private int capacity;

    /* Constructors */
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /* Public setters and getters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /* Overriding toString */
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Room)) {
            return false;
        }

        Room r = (Room) o;
        return r.name.equals(this.name)
                && (r.capacity == this.capacity);
    }
}
