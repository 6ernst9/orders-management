package org.example.presentation.updatewindow;

import org.example.controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UpdateProductWindow {
    private final ProductController productController;
    private JFrame frame;
    private JTextField productIdField, nameField, priceField, quantityField;

    public UpdateProductWindow(final ProductController productController) {
        this.productController = productController;

        frame = new JFrame("Update Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(20);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long productId = Long.parseLong(productIdField.getText());
                String name = nameField.getText();
                float price = Float.parseFloat(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                try {
                    productController.updateProduct(productId, name, price, quantity);
                    showResultDialog("Product updated successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog("Error updating product.", "Error");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(productIdLabel);
        panel.add(productIdField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel());
        panel.add(updateButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showResultDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
