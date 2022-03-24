import com.github.javafaker.Faker;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class City {
    private String name;
    private List<Street> streets = new LinkedList<>();
    private Map<Intersection, List<Street>> cityMap = new HashMap<>();
    Set<Intersection> intersectionSet;

    City(String name) {
        this.name = name;
    }

    public void compulsory() {
        Faker faker = new Faker();
        var intersections = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new Intersection(faker.name().lastName() + i))
                .toArray(Intersection[]::new);

        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 1));
        streets.add(new Street(faker.name().lastName(), 3));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 3));
        streets.add(new Street(faker.name().lastName(), 1));
        streets.add(new Street(faker.name().lastName(), 1));
        streets.add(new Street(faker.name().lastName(), 3));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 1));
        streets.add(new Street(faker.name().lastName(), 2));
        streets.add(new Street(faker.name().lastName(), 1));
        streets.add(new Street(faker.name().lastName(), 1));

        Collections.sort(streets, (a, b) -> a.compareTo(b));
        for (Street s : streets) {
            System.out.println(s.toString());
        }
        System.out.println();

        intersectionSet = new HashSet<Intersection>(List.of(intersections));
        for (Intersection i : intersectionSet) {
            System.out.println(i.toString());
        }

        // Create a stream to filter out possible distinctions in the "Set".
        Set<Intersection> testSet = intersectionSet.stream()
                .distinct()
                .collect(Collectors.toSet());
        assert (testSet.equals(intersectionSet));
    }

    private int neighbours(Street s) {
        int nr = 0;
        for (var entry : cityMap.entrySet()) {
            if (entry.getValue().contains(s))
                nr += (entry.getValue().size() - 1);
        }
        return nr;
    }

    // This method will try to connect the intersections and streets according to the example.
    public void buildCity() {
        List<Intersection> intersectionList = new ArrayList<>(intersectionSet);
        cityMap.put(intersectionList.get(0), Arrays.asList(streets.get(0), streets.get(1), streets.get(2)));
        cityMap.put(intersectionList.get(1), Arrays.asList(streets.get(0), streets.get(3), streets.get(10)));
        cityMap.put(intersectionList.get(2), Arrays.asList(streets.get(2), streets.get(5), streets.get(4)));
        cityMap.put(intersectionList.get(3), Arrays.asList(streets.get(5), streets.get(6), streets.get(7)));
        cityMap.put(intersectionList.get(4), Arrays.asList(streets.get(1), streets.get(3), streets.get(4), streets.get(6), streets.get(13)));
        cityMap.put(intersectionList.get(5), Arrays.asList(streets.get(8), streets.get(9), streets.get(10), streets.get(11)));
        cityMap.put(intersectionList.get(6), Arrays.asList(streets.get(9), streets.get(12), streets.get(11)));
        cityMap.put(intersectionList.get(7), Arrays.asList(streets.get(7), streets.get(11), streets.get(14), streets.get(15)));
        cityMap.put(intersectionList.get(8), Arrays.asList(streets.get(12), streets.get(13), streets.get(15)));

        streets.stream()
                .filter(e -> e.getLength() > 1)
                .filter(e -> neighbours(e) >= 3)
                .forEach(System.out::println);
    }

    //public void kruskal() {
    //Graph<Intersection, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
    //for (var in : cityMap.keySet())
    //  graph.addVertex(in);

    //}

    private Intersection neighbour(Intersection node, Street street) {
        for (var iterator : cityMap.entrySet()) {
            if (iterator.getValue().contains(street)
                    && !iterator.getKey().equals(node)
                    && !iterator.getKey().visited())
                return iterator.getKey();
        }
        return null;
    }

    public void prim() {
        Intersection[] arr = intersectionSet.stream()
                .toArray(Intersection[]::new);
        Intersection node = arr[0];
        node.setVisited();
        PriorityQueue<Street> priorityQueue = new PriorityQueue<>();
        int visitedNodes = 1;

        while (visitedNodes < cityMap.keySet().size()) {
            for (var street : cityMap.get(node)) {
                if (!street.isVisited())
                    priorityQueue.add(street);
            }
            Street newStreet = priorityQueue.poll();
            while ((node = neighbour(node, newStreet)) == null) {
                newStreet = priorityQueue.poll();
            }
            node.setVisited();
            newStreet.visit();
            visitedNodes++;
        }

        int sum = 0;
        System.out.println();
        for (var street : streets) {
            if (street.isVisited()) {
                System.out.println(street);
                sum += street.getLength();
            }
        }
        System.out.println(sum);
    }
}
