package main;

import config.Config;

import java.util.ArrayList;

public class Circle {
    private final Config config;
    private final int x;
    private final int y;
    private ArrayList<Circle> neighbors;
    private int availableNeighbors;
    private int color; // 1 - blue, 2 - red, 0 - default

    public Circle (int x, int y, Config config) {
        this.x = x;
        this.y = y;
        this.config = config;
        neighbors = new ArrayList<>();
        color = config.DEF;

        // Determine the number of available neighbors. (3 if on margin)
        if (isMarginal()) {
            availableNeighbors = 3;
        } else {
            availableNeighbors = 4;
        }
    }

    public boolean isMarginal() {
        return x == config.getStartX() || y == config.getStartY()
                || x == config.getEndX() || y == config.getEndY();
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Circle c)) {
            return false;
        }

        return this.x == c.getX() && this.y == c.getY();
    }

    public boolean isNeighbor (Circle c) {
        if (this.equals(c))
            return true;

        if (neighbors.isEmpty())
            return false;

        for (Circle circle : neighbors) {
            if (circle.equals(c))
                return true;
        }
        return false;
    }

    public void addNeighbor (Circle c) {
        neighbors.add(c);
        availableNeighbors--;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Circle> getNeighbors() {
        return neighbors;
    }

    public int getAvailableNeighbors() {
        return availableNeighbors;
    }
}
