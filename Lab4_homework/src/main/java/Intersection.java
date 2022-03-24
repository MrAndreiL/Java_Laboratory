public class Intersection implements Entity, Comparable<Intersection>{
    private final String name;
    private int cost = Integer.MAX_VALUE;
    private boolean isVisited;

    Intersection (String name) {
        this.name = name;
        isVisited = false;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setVisited() {
        isVisited = true;
    }

    public boolean visited() {
        return isVisited;
    }

    // Interface implemented
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Intersection{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Intersection i1) {
        if (i1 == null)
            return 0;

        return this.cost > i1.cost ? 1 : -1;
    }
}
