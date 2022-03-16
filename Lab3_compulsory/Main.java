package com.company;

import java.util.WeakHashMap;

public class Main {

    public static void main(String[] args) {
        Network n1 = new Network("Retea 1");
        n1.addNode(new Computer("v1", "125", 10));
        n1.addNode(new Computer("v6", "126", 5));
        n1.addNode(new Switch("v4"));
        n1.addNode(new Switch("v3"));
        n1.addNode(new Router("v2", "400"));
        n1.addNode(new Router("v5", "404"));
        n1.printNodes();
    }
}
