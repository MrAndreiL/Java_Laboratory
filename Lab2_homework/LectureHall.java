package com.company;
/*
    @author Lungu Andrei-Sebastian
 */
public class LectureHall extends Room {
    private boolean hasProjector;

    LectureHall (String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    public boolean getProjector() {
        return hasProjector;
    }

    public void setProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}
