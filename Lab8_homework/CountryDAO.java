package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CountryDAO implements DAO<Country> {
    public void create(Country country) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con
                .prepareStatement("insert into countries(id, name, code, continent) values (?,?,?,?)")) {
            pstmt.setInt(1, country.getId());
            pstmt.setString(2, country.getName());
            pstmt.setInt(3, country.getCode());
            pstmt.setString(4, country.getContinent());
            pstmt.execute();
        }
    }

    public Country findById(Country country) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM countries WHERE id='" + country.getId() + "'");
            rs.next();
            return new Country(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
        }
    }

    public Country findByName(Country country) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM countries WHERE name='" + country.getName() + "'");
            rs.next();
            return new Country(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
        }
    }
}
