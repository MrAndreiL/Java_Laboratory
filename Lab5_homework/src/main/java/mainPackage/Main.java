package mainPackage;

import Exceptions.InfoException;
import Exceptions.InvalidCatalogExpression;
import Exceptions.ReportException;
import Exceptions.ViewException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("Management System");
        Item book = new Item("knuth67", "The art of computer programming", "/home/andrei/Desktop/art.pdf");
        book.addTag("1", "string");
        Item docs = new Item("java17", "The Java Language Specification", "/home/andrei/Desktop/jls18.pdf");
        docs.addTag("2", "string2");
        AddCommand.Add(catalog.getItems(), book);
        AddCommand.Add(catalog.getItems(), docs);
        System.out.println(catalog.toString());

        try {
            SaveCommand.save("/home/andrei/Work/Lab5/src/main/java/mainPackage/catalog.json", catalog);
        } catch (IOException ex){
            ex.printStackTrace();
        }

        try {
            Catalog cat2 = LoadCommand.load("/home/andrei/Work/Lab5/src/main/java/mainPackage/catalog.json");
            System.out.println(cat2.toString());
        } catch (InvalidCatalogExpression ex) {
            ex.printStackTrace();
        }

        ListCommand.list(catalog.getItems());

        try {
            ViewCommand.View(catalog.getItems().get(0).getLocation());
        } catch (ViewException ex) {
            ex.printStackTrace();
        }

        try {
            ReportCommand.report(catalog);
        } catch (ReportException ex) {
            ex.printStackTrace();
        }

        try {
            InfoCommand.info();
        } catch (IOException | InfoException ex) {
            ex.printStackTrace();
        }
    }
}
