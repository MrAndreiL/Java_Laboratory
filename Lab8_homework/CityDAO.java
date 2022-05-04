package com.example;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class CityDAO implements DAO<City> {
    @Override
    public void create(City city) throws SQLException {
        Connection conn = Database.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(
                "insert into cities(id, country, name, capital, latitude, longitude) values (?,?,?,?,?,?)")) {
            pstmt.setInt(1, city.getId());
            pstmt.setString(2, city.getCountry());
            pstmt.setString(3, city.getName());
            pstmt.setString(4, city.getCapital());
            pstmt.setDouble(5, city.getLatitude());
            pstmt.setDouble(6, city.getLongitude());
            pstmt.execute();
        }
    }

    @Override
    public City findById(City city) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM cities WHERE id='" + city.getId() + "'");
            rs.next();
            return new City(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6));
        }
    }

    @Override
    public City findByName(City city) throws SQLException {
        Connection conn = Database.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM cities WHERE name='" + city.getName() + "'");
            rs.next();
            return new City(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getDouble(6));
        }
    }
}
