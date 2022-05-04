package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/java";
    private static final String USER = "root";
    private static final String password = "";

    private static Connection connection = null;

    private Database() {

    }

    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Cannot connect to the DB " + ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
