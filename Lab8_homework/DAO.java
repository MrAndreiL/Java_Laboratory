package com.example;

import java.sql.SQLException;

public interface DAO<T> {
    public void create(T obj) throws SQLException;

    public T findByName(T obj) throws SQLException;

    public T findById(T obj) throws SQLException;
}
