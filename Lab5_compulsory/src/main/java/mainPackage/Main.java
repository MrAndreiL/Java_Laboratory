package mainPackage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("Management System");
        Item book = new Item("knuth67", "The art of computer programming", "d:/books/programming/tacp.pso");
        book.addTag("1", "string");
        Item docs = new Item("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html");
        docs.addTag("2", "string2");
        catalog.add(book);
        catalog.add(docs);
        System.out.println(catalog.toString());

        try {
            CatalogUtil.save("/home/andrei/Work/Lab5/src/main/java/mainPackage/catalog.json", catalog);
        } catch (IOException ex){
            ex.printStackTrace();
        }

        try {
            Catalog cat2 = CatalogUtil.load("/home/andrei/Work/Lab5/src/main/java/mainPackage/catalog.json");
            System.out.println(cat2.toString());
        } catch (InvalidCatalogExpression ex) {
            ex.printStackTrace();
        }

    }
}
