package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node> {
    protected String name;
    private Map<Node, Integer> connections = new HashMap<Node, Integer>();
    private int distance = Integer.MAX_VALUE;
    private boolean isVisited;

    public Node(String name) {
        this.name = name;
        isVisited = false;
    }

    public String getName() {
        return name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance () {
        return distance;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public Map<Node, Integer> getConnections() {
        return connections;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean isConnection(Node node) {
        for (Node n : connections.keySet()) {
            if (node.compareTo(n) == 0)
                return true;
        }
        return false;
    }

    public void addConnection(Node node, int cost) {
        // Check if the connection is already established.
        if (!isConnection(node)) {
            connections.put(node, cost);
        }
    }

    public void printConnections() {
        System.out.printf("%s -> ", name);
        for (Map.Entry<Node, Integer> entry : connections.entrySet()) {
            System.out.printf("{%s %d}", entry.getKey().getName(), entry.getValue());
        }
        System.out.println();
    }

    public String getAddr() {
        if (this instanceof Identifiable) {
            return ((Identifiable) this).getAddress();
        }
        return null;
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
