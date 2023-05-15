package org.example.presentation.viewwindow;

import org.example.controller.ProductController;
import org.example.model.Product;
import org.example.presentation.TableUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewProductsWindow {
    private final ProductController productController;
    private JFrame frame;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ViewProductsWindow(final ProductController productController) throws SQLException {
        this.productController = productController;

        frame = new JFrame("View Products");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = TableUtil.createTable(productController.getProductService().getProductRepository(), Product.class);
        productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(productTable);
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText();
                searchProductsById(searchId);
            }
        });
        searchPanel.add(new JLabel("Search by ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void searchProductsById(String textId) {
        tableModel.setRowCount(0);
        if(Objects.equals(textId, "") || Objects.equals(textId, " ")){
            getAllProducts();
            return;
        }
        long id = Long.parseLong(textId);
        try {
            Product product = productController.getProductById(id);
            if (product != null) {
                Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getQuantity()};
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Product not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getAllProducts(){
        ArrayList<Product> productList = null;
        try {
            productList = productController.getProducts();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        assert productList != null;
        for (Product product : productList) {
            Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getQuantity()};
            tableModel.addRow(row);
        }
    }
}
