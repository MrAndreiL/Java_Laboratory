package com.example;

import java.sql.SQLException;

import java.lang.Math;

public class App {
    public static void main(String[] args) {
        Tool tool = new Tool(
                "E:\\concap.csv");
        tool.addToDatabase();

        // 1. Extract the city with id 1.
        CityDAO cities = new CityDAO();

        City city = new City(1, "Romania", "Bucuresti", "1", 22.5, 23.4);
        try {
            city = cities.findById(city);
            double sLong = city.getLongitude();
            double sLat = city.getLatitude();

            // 2. Iterate through the rest of the db and display the distance.
            for (int i = 2; i <= 200; i++) {
                city.setId(i);
                city = cities.findById(city);
                double fLong = city.getLongitude();
                double fLat = city.getLatitude();
                distance(sLat, fLat, sLong, fLong);
            }

            // 3. Close connection to the DB.
            Database.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void distance(double lat1, double lat2, double lon1, double lon2) {
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                        * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        System.out.println(c * r);
    }

}
