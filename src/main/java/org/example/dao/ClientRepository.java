package org.example.dao;
import org.example.model.Client;

import java.sql.*;
import java.util.ArrayList;

public class ClientRepository implements DaoRepository {
    private final Connection connection;
    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type> void add(Type object) throws SQLException {
        Client client = (Client)object;
        String sql = "INSERT INTO client (name, email, age ) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, client.getName());
        statement.setString(2, client.getEmail());
        statement.setInt(3, client.getAge());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            client.setId(resultSet.getLong(1));
        }
    }
    @Override
    public <Type> void update(Type object) throws SQLException {
        Client client = (Client)object;
        String sql = "UPDATE client SET name = ?, email = ?, age = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, client.getName());
        statement.setString(2, client.getEmail());
        statement.setInt(3, client.getAge());
        statement.setLong(4, client.getId());
        statement.executeUpdate();
    }
    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
    }
    @Override
    public ArrayList<Client> findAll() throws SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT * FROM client";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getLong("id"));
            client.setName(resultSet.getString("name"));
            client.setEmail(resultSet.getString("email"));
            client.setAge(resultSet.getInt("age"));
            clients.add(client);
        }
        return clients;
    }
    @Override
    public Client findById(Long id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Client client = null;
        if (resultSet.next()) {
            client = new Client();
            client.setId(resultSet.getLong("id"));
            client.setName(resultSet.getString("name"));
            client.setEmail(resultSet.getString("email"));
            client.setAge(resultSet.getInt("age"));
        }
        return client;
    }
}