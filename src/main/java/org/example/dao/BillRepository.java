package org.example.dao;

import org.example.model.Bill;
import org.example.model.BillGenerator;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;

public class BillRepository{
    private final Connection connection;
    private final ProductRepository productRepository;
    public BillRepository(Connection connection, ProductRepository productRepository) {
        this.connection = connection;
        this.productRepository = productRepository;
    }
    public void add(Bill bill) throws SQLException {
        String sql = "INSERT INTO bill (datee, time, clientid, productid, price, quantity  ) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, bill.getDate());
        statement.setString(2, bill.getTime());
        statement.setLong(3, bill.getClientId());
        statement.setLong(4, bill.getProductID());
        statement.setFloat(5, bill.getPrice());
        statement.setInt(6, bill.getQuantity());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            bill.setId(resultSet.getLong(1));
        }

        Product product = productRepository.findById(bill.getProductID());
        BillGenerator.generateBill(bill, product.getName(), product.getPrice());
    }

    public ArrayList<Bill> findAll() throws SQLException {
        ArrayList<Bill> bills = new ArrayList<Bill>();
        String sql = "SELECT * FROM bill";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Bill bill = new Bill();
            bill.setId(resultSet.getLong("id"));
            bill.setDate(resultSet.getString("datee"));
            bill.setTime(resultSet.getString("time"));
            bill.setClientId(resultSet.getLong("clientId"));
            bill.setClientId(resultSet.getLong("productId"));
            bill.setPrice(resultSet.getFloat("price"));
            bill.setQuantity(resultSet.getInt("quantity"));
            bills.add(bill);
        }
        return bills;
    }

}
