
public class Intersection implements Entity{
    private final String name;

    Intersection (String name) {
        this.name = name;
    }

    // Interface implemented
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Intersection{" +
                "name='" + name + '\'' +
                '}';
    }
}
