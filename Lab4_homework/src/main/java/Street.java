import java.util.Comparator;

public class Street implements Comparable<Street>, Entity {
    private final String name;
    private int length;
    private boolean visited = false;
    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }

    Street (String name, int length) {
        this.name   = name;
        this.length = length;
    }

    public void setLength(int length)  { this.length = length; }

    public int getLength() { return length; }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    // Interface implemented
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Street))
            return false;

        if (!this.getName().equals(((Street) o).getName()))
            return false;

        return this.length == ((Street) o).length;
    }

    @Override
    public int compareTo(Street s1) {
        if (s1 == null)
            return 0;

        if (this.equals(s1)) return 0;

        return this.length > s1.length ? 1 : -1;
    }
}

class SortByLength implements Comparator<Street> {
    public int compare(Street s1, Street s2) {
        return s1.compareTo(s2);
    }
}
