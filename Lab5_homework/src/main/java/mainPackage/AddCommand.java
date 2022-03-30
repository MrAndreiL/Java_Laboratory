package mainPackage;

import java.util.List;

public class AddCommand implements Commandable {
    public static void Add(List<Item> list, Item item) {
        list.add(item);
    }
}
