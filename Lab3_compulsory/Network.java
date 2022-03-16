package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Network {
    private String name;
    private List<Node> nodes = new ArrayList<Node>();

    public Network(String name) {
        this.name = name;
    }

    private boolean inList(List<Node> nodes, Node node) {
        for (Node n : nodes) {
            if (n.compareTo(node) == 0)
                return true;
        }
        return false;
    }

    /**
     * Add a new node. Checks if the node already exists.
     * @param node A new node to be added to the network.
     */
    public void addNode(Node node) {
        if (nodes.isEmpty() || !inList(nodes, node)) {
            nodes.add(node);
            Collections.sort(nodes);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printNodes() {
        for (Node node : nodes) {
            System.out.println(node.toString());
        }
    }

    @Override
    public String toString() {
        return "Network{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
