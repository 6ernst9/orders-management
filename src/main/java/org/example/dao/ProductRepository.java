package org.example.dao;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository implements DaoRepository {
    private final Connection connection;
    public ProductRepository(Connection connection) {
        this.connection = connection;
    }
    @Override
    public <Type> void add(Type object) throws SQLException {
        Product product =(Product) object;
        String sql = "INSERT INTO product (name, price, quantity ) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            product.setId(resultSet.getLong(1));
        }
    }
    @Override
    public <Type> void update(Type object) throws SQLException {
        Product product =(Product) object;
        String sql = "UPDATE product SET name = ?, price = ?, quantity = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setFloat(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setLong(4, product.getId());
        statement.executeUpdate();
    }
    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
    }
    @Override
    public ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM product";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
            product.setQuantity(resultSet.getInt("quantity"));
            products.add(product);
        }
        return products;
    }
    @Override
    public Product findById(Long id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Product product = null;
        if (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getFloat("price"));
            product.setQuantity(resultSet.getInt("quantity"));
        }
        return product;
    }
}