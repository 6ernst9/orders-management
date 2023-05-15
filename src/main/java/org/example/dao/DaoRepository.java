package org.example.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DaoRepository {
    <Type> void add(Type t) throws SQLException;
    void delete(Long id) throws SQLException;
    <Type> void update(Type t) throws SQLException;
    <Type> ArrayList<Type> findAll() throws SQLException;
    <Type> Type findById(Long id) throws SQLException;
}
