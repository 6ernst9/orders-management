package org.example.presentation.addwindow;

import org.example.controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddProductWindow {
    private final ProductController productController;
    private JFrame frame;
    private JTextField nameField, priceField, quantityField;

    public AddProductWindow(final ProductController productController) {
        this.productController = productController;

        frame = new JFrame("Add Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                float price = Float.parseFloat(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                try {
                    productController.addProduct(name, price, quantity);
                    showResultDialog("Product added successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog("Error adding product.", "Error");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel());
        panel.add(addButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showResultDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
