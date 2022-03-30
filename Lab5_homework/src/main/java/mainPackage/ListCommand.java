package mainPackage;

import java.util.List;

public class ListCommand {
    public static void list(List<Item> l) {
        l.forEach(System.out::println);
    }
}
