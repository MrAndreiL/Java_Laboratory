package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public class Event {
    private String name;
    private int size;
    private int startTime;
    private int endTime;

    public Event(String name, int size, int startTime, int endTime) {
        this.name = name;
        this.size = size;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared to itself then return true.
        if (o == this) {
            return true;
        }

        // Check if o is an instance of Event or not.
        if (!(o instanceof Event)) {
            return false;
        }

        Event e = (Event) o;
        return e.name.equals(this.name)
                && (e.size == this.size)
                && (e.startTime == this.startTime)
                && (e.endTime == this.endTime);
    }
}
