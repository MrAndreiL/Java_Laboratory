package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CountryDAO {
    public void create(String name, int code, String continent) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (PreparedStatement pstmt = con
                    .prepareStatement("insert into countries(name, code, continent) values (?, ?, ?)")) {
                pstmt.setString(1, name);
                pstmt.setInt(2, code);
                pstmt.setString(3, continent);
                pstmt.executeUpdate();
            }
        }
    }

    public String findById(int id) throws SQLException {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select name from countries where id='" + id + "'");
                return rs.next() ? rs.getString(1) : "";
            }
        }
        return "";
    }

    public int findByName(String name) throws SQLException {
        Connection conn = Database.getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select id from countries where name='" + name + "'");
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
        return -1;
    }
}
