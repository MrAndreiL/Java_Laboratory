package com.company;

import java.util.*;

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
     * Checks if a node given as String exists in the list.
     * @param node The node(String) to be checked.
     * @return the node given as Node if true, null otherwise.
     */
    private Node getNode(String node) {
        for (Node n : nodes) {
            if (n.getName().equals(node))
                return n;
        }
        return null;
    }

    public void connect(String node1, String node2, int cost) {
        Node n1 = getNode(node1);
        Node n2 = getNode(node2);
        if (n1 != null && n2 != null) {
            n1.addConnection(n2, cost);
            n2.addConnection(n1, cost);
        }
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
            node.printConnections();
        }
    }

    public void displayIden() {
        List<Node> idenNodes = new ArrayList<Node>();
        for (Node n : nodes) {
            if (n instanceof Identifiable)
                idenNodes.add(n);
        }
        Collections.sort(idenNodes, new SortByAddress());
        System.out.println(idenNodes);
    }

    @Override
    public String toString() {
        return "Network{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }

    public void dijkstra(String src) {
        Node start = getNode(src);

        if (start != null) {
            start.setDistance(0);
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(start);
            start.setVisited(true);

            while (!priorityQueue.isEmpty()) {
                Node vertex = priorityQueue.poll();

                for (Map.Entry<Node, Integer> neighbour : vertex.getConnections().entrySet()) {
                    int newDistance = vertex.getDistance() + neighbour.getValue();
                    if (newDistance < neighbour.getKey().getDistance()){
                        neighbour.getKey().setDistance(newDistance);
                        priorityQueue.add(neighbour.getKey());
                    }
                }
            }
            for (Node n : nodes) {
                System.out.println(n.getDistance());
            }
        }
    }
}

class SortByAddress implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return n1.getAddr().compareTo(n2.getAddr());
    }
}