import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var intersections = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new Intersection("v" + i))
                .toArray(Intersection[]::new);


        List<Street> streets = new LinkedList<>();
        streets.add(new Street("Street 1", 2));
        streets.add(new Street("Street 2", 2));
        streets.add(new Street("Street 3", 2));
        streets.add(new Street("Street 4", 2));
        streets.add(new Street("Street 5", 1));
        streets.add(new Street("Street 6", 3));
        streets.add(new Street("Street 7", 2));
        streets.add(new Street("Street 8", 3));
        streets.add(new Street("Street 9", 1));
        streets.add(new Street("Street 10", 1));
        streets.add(new Street("Street 11", 3));
        streets.add(new Street("Street 12", 2));
        streets.add(new Street("Street 13", 1));
        streets.add(new Street("Street 14", 2));
        streets.add(new Street("Street 15", 1));
        streets.add(new Street("Street 16", 1));

        Collections.sort(streets, (a, b) -> a.compareTo(b));
        for (Street s : streets) {
            System.out.println(s.toString());
        }
        System.out.println();

        Set<Intersection> intersectionSet = new HashSet<Intersection>(List.of(intersections));
        for (Intersection i : intersectionSet) {
            System.out.println(i.toString());
        }

        // Create a stream to filter out possible distinctions in the "Set".
        Set<Intersection> testSet = intersectionSet.stream()
                .distinct()
                .collect(Collectors.toSet());
        assert(testSet.equals(intersectionSet));
    }
}
