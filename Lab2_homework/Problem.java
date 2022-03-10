package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
import java.util.ArrayList;

public class Problem {
    public ArrayList<Room> rooms;
    public ArrayList<Event> events;

    Problem() {
        rooms  = new ArrayList<Room>();
        events = new ArrayList<Event>();
    }

    public void addEvent(Event event) {
        for (Event e : events) {
            if (e.equals(event)) {
                System.out.println("An event has been added twice");
                return;
            }
        }
        events.add(event);
    }

    public void printEvents() {
        for (Event e : events) {
            System.out.println(e.toString());
        }
    }

    public int getNrEvents() {
        return events.size();
    }

    public int getNrRooms() { return rooms.size(); }

    public void addRoom(Room room) {
        for (Room r : rooms) {
            if (r.equals(room)) {
                System.out.println("A room has been added twice");
                return;
            }
        }
        rooms.add(room);
    }

    public void printRooms() {
        for (Room r : rooms) {
            System.out.println(r.toString());
        }
    }
}
