package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public class Main {

    public static void main(String[] args) {
        Problem p1 = new Problem();

        p1.addEvent(new Event("C1", 100, 8, 10));
        p1.addEvent(new Event("C2", 100, 10, 12));
        p1.addEvent(new Event("L1", 30, 8, 10));
        p1.addEvent(new Event("L2", 30, 8, 10));
        p1.addEvent(new Event("L3", 30, 10, 12));
        p1.printEvents();

        p1.addRoom(new Lab("401", 30, "Linux"));
        p1.addRoom(new Lab("403", 30, "Windows"));
        p1.addRoom(new Lab("405", 30, "Mac OSX"));
        p1.addRoom(new LectureHall("309", 100, true));
        p1.printRooms();

        Solution s1 = new Solution(p1);
        s1.solve();
    }
}
