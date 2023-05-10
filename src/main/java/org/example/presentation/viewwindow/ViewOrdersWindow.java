package org.example.presentation.viewwindow;

import org.example.controller.OrderController;
import org.example.model.Order;
import org.example.model.OrderFilterEnum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewOrdersWindow {
    private final OrderController orderController;
    private JFrame frame;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ViewOrdersWindow(final OrderController orderController) {
        this.orderController = orderController;

        frame = new JFrame("View Orders");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Order ID");
        tableModel.addColumn("Client ID");
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Price");

        orderTable = new JTable(tableModel);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(orderTable);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);

        getAllOrdersByFilter(OrderFilterEnum.NONE, "");
        JButton searchButton1 = new JButton("Search");
        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText();
                searchOrdersById(searchId);
            }
        });

        JButton searchButton2 = new JButton("Search by client");
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText();
                getAllOrdersByFilter(OrderFilterEnum.BY_CLIENT_ID, searchId);
            }
        });

        JButton searchButton3 = new JButton("Search by product");
        searchButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText();
                getAllOrdersByFilter(OrderFilterEnum.BY_PRODUCT_ID, searchId);
            }
        });

        searchPanel.add(new JLabel("Search by ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton1);
        searchPanel.add(searchButton2);
        searchPanel.add(searchButton3);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void searchOrdersById(String textId) {
        tableModel.setRowCount(0);
        if (Objects.equals(textId, "") || Objects.equals(textId, " ")) {
            getAllOrdersByFilter(OrderFilterEnum.NONE, "");
            return;
        }
        long id = Long.parseLong(textId);
        try {
            Order order = orderController.getOrderById(id);
            if (order != null) {
                Object[] row = {order.getId(), order.getClientID(), order.getProductID(), order.getQuantity(), order.getPrice()};
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Order not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getAllOrdersByFilter(OrderFilterEnum filter, String textId) {
        ArrayList<Order> orderList = null;
        long id=0;
        if(filter != OrderFilterEnum.NONE){
            id = Long.parseLong(textId);
        }
        tableModel.setRowCount(0);

        try {
            if(filter == OrderFilterEnum.NONE)
                orderList = orderController.getOrders();
            else if(filter == OrderFilterEnum.BY_CLIENT_ID)
                orderList = orderController.getOrdersByClientId(id);
            else if(filter == OrderFilterEnum.BY_PRODUCT_ID)
                orderList = orderController.getOrdersByProductId(id);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        assert orderList != null;
        for (Order order : orderList) {
            Object[] row = {order.getId(), order.getClientID(), order.getProductID(), order.getQuantity(), order.getPrice()};
            tableModel.addRow(row);
        }
    }
}
