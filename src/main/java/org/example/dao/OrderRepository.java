package org.example.dao;
import org.example.model.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {
    private final Connection connection;
    public OrderRepository(Connection connection) {
        this.connection = connection;
    }
    public void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO package(clientid, productid, price, quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, order.getClientID());
        statement.setLong(2, order.getProductID());
        statement.setFloat(3, order.getPrice());
        statement.setInt(4, order.getQuantity());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            order.setId(resultSet.getLong(1));
        }
    }

    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE package SET clientid = ?, productid = ?, price = ?, quantity = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, order.getClientID());
        statement.setLong(2, order.getProductID());
        statement.setFloat(3, order.getPrice());
        statement.setInt(4, order.getQuantity());
        statement.setLong(5, order.getId());
        statement.executeUpdate();
    }

    public void deleteOrder(Long id) throws SQLException {
        String sql = "DELETE FROM package WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    public ArrayList<Order> findAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM package";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setClientID(resultSet.getLong("clientId"));
            order.setProductID(resultSet.getLong("productId"));
            order.setPrice(resultSet.getFloat("price"));
            order.setQuantity(resultSet.getInt("quantity"));
            orders.add(order);
        }
        return orders;
    }

    public Order findOrderById(Long id) throws SQLException {
        String sql = "SELECT * FROM package WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Order order = null;
        if (resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setClientID(resultSet.getLong("clientId"));
            order.setProductID(resultSet.getLong("productId"));
            order.setPrice(resultSet.getFloat("price"));
            order.setQuantity(resultSet.getInt("quantity"));
        }
        return order;
    }

    public ArrayList<Order> findOrdersByClientId(Long id) throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM package WHERE clientid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setClientID(resultSet.getLong("clientId"));
            order.setProductID(resultSet.getLong("productId"));
            order.setPrice(resultSet.getFloat("price"));
            order.setQuantity(resultSet.getInt("quantity"));
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<Order> findOrdersByProductId(Long id) throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM package WHERE productid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setClientID(resultSet.getLong("clientId"));
            order.setProductID(resultSet.getLong("productId"));
            order.setPrice(resultSet.getFloat("price"));
            order.setQuantity(resultSet.getInt("quantity"));
            orders.add(order);
        }
        return orders;
    }
}