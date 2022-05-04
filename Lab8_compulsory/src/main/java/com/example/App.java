package com.example;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            Database.createConnection();

            ContinentDAO continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();

            CountryDAO countries = new CountryDAO();
            int europeID = continents.findByName("Europe");
            System.out.println(europeID);
            countries.create("Romania", europeID, "Europe");
            countries.create("Ukraine", europeID, "Europe");
            Database.getConnection().commit();

            Database.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
