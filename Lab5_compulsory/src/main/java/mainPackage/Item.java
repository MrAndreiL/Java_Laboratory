package mainPackage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Item implements Imitable, Serializable {
    private String id;
    private String title;
    private String location;

    private Map<String, Object> tags = new HashMap<>();

    public Item(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }

    public Item() {

    }

    @Override
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Item itemObj)) {
            return false;
        }

        return itemObj.getId().equals(this.id) &&
                itemObj.getLocation().equals(this.location) &&
                itemObj.getTitle().equals(this.title);
    }
}
