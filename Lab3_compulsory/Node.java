package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node> {
    protected String name;
    // private Map<Node, Integer> cost = new HashMap<Node, Integer>();

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check if two nodes in a network have the same name.
     * @param other the node compared with
     * @return 0 if they are equal or -1 if one of the is null.
     */
    @Override
    public int compareTo(Node other) {
        if (other.name == null || this.name == null) {
            return 0;
        }
        return this.name.compareTo(other.name);
    }
}
