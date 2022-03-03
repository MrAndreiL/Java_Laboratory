package com.company;

public class Main {

    public static void main(String[] args) {
        // Demo for rooms and classes.
        Room R1 = new Room("401", 30, RoomType.LABORATORY);

        Room R2 = new Room("401", 30, RoomType.LABORATORY);

        Room R3 = new Room("401", 30, RoomType.LECTURE_HALL);

        System.out.println(R1.toString());
        System.out.println(R2.toString());
        System.out.println(R3.toString());

        Event E1 = new Event("C1", 100, 8, 10);

        Event E2 = new Event("C2", 100, 10, 12);

        Event E3 = new Event("L1", 30, 8, 10);

        Event E4 = new Event("L2", 30, 8, 10);

        Event E5 = new Event("L3", 30, 10, 12);

        System.out.println(E1.toString());
        System.out.println(E2.toString());
        System.out.println(E3.toString());
        System.out.println(E4.toString());
    }
}
