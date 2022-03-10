package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public class Solution {
    private StringBuilder[] solution;
    private Problem problem;

    Solution (Problem problem) {
        this.problem = problem;
        solution = new StringBuilder[problem.events.size()];
    }

    /*
       solve iterates through the events and tries to match with
       a room using the @roomProgram array which stores the starting
        time of the events matched.
     */
    public void solve() {
        int[] roomProgram = new int[problem.getNrRooms()];

        int it = 0;
        for (Event e : problem.events) {
            int size  = e.getSize();
            int start = e.getStartTime();
            for (int i = 0; i < problem.rooms.size(); i++) {
                if (problem.rooms.get(i).getCapacity() >= size && start > roomProgram[i]) {
                    solution[it] = new StringBuilder();
                    solution[it].append(problem.rooms.get(i).getName());
                    it++;
                    roomProgram[i] = start;
                    break;
                }
            }
        }

        for (int i = 0; i < it; i++) {
            System.out.println(problem.events.get(i).getName() + " -> " + solution[i]);
        }
    }
}
