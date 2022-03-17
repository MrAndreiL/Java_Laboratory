package com.company;

public class Main {

    public static void main(String[] args) {
        Network n1 = new Network("Retea 1");
        n1.addNode(new Computer("v1", "125", 10));
        n1.addNode(new Computer("v6", "126", 5));
        n1.addNode(new Switch("v4"));
        n1.addNode(new Switch("v3"));
        n1.addNode(new Router("v2", "400"));
        n1.addNode(new Router("v5", "404"));
        n1.connect("v5", "v6", 20);
        n1.connect("v2", "v3", 20);
        n1.connect("v4", "v6", 10);
        n1.connect("v1", "v2", 10);
        n1.connect("v1", "v3", 50);
        n1.connect("v2", "v4", 20);
        n1.connect("v2", "v5", 20);
        n1.connect("v3", "v4", 10);
        n1.connect("v5", "v6", 20);
        n1.connect("v4", "v5", 30);
        n1.printNodes();
        n1.displayIden();
        n1.dijkstra("v5");
    }
}
