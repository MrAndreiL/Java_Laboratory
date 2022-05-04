package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ContinentDAO implements DAO<Continent> {
    public void create(Continent continent) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into continents(id, name) values (?,?)")) {
            pstmt.setInt(1, continent.getId());
            pstmt.setString(2, continent.getName());
            pstmt.execute();
        }
    }

    public Continent findByName(Continent continent) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM continents WHERE name='" + continent.getName() + "'");
            rs.next();
            return new Continent(rs.getInt(1), rs.getString(2));
        }
    }

    public Continent findById(Continent continent) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM continents WHERE id='" + continent.getId() + "'");
            rs.next();
            return new Continent(rs.getInt(1), rs.getString(2));
        }
    }
}
