package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ContinentDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (PreparedStatement pstmt = con.prepareStatement("insert into continents(name) values (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        } else {
            System.out.println("Error at accessing database object");
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select id from continents where name='" + name + "'");
                return rs.next() ? rs.getInt(1) : null;
            }
        }
        return -1;
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery("select name from continents where id='" + id + "'");
                return rs.next() ? rs.getString(1) : null;
            }
        }
        return "";
    }
}
