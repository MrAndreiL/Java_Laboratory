package main;

import config.Config;

public class Game {
    private final Config config;
    private int turn; // Establishes whose turn it is.
    private Circle[][] grid;

    public Game(Config config) {
        turn = 1;
        this.config = config;

        grid = new Circle[config.getRows()][config.getColumns()]; // uninitialized, do that when drawing
    }

    public Circle[][] getGrid() { return grid; }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
