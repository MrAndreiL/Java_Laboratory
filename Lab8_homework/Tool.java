package com.example;

import java.io.*;

import java.sql.SQLException;

public class Tool {
    private final String path;

    public Tool(String path) {
        this.path = path;
    }

    public void addToDatabase() {
        File file = new File(path);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = " ";
                String[] tempArr;
                int id = 1;
                CityDAO cities = new CityDAO();
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    City city = new City(id++, tempArr[0], tempArr[1], "1", Double.parseDouble(tempArr[2]),
                            Double.parseDouble(tempArr[3]));
                    cities.create(city);
                }
                Database.getConnection().commit();
                br.close();
                file.delete();
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
