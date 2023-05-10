package org.example.presentation.addwindow;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddOrderWindow {
    private final OrderController orderController;
    private JFrame frame;
    private JTextField clientIdField, productIdField, quantityField;

    public AddOrderWindow(final OrderController orderController) {
        this.orderController = orderController;

        frame = new JFrame("Add Order");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField(20);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long clientId = Long.parseLong(clientIdField.getText());
                long productId = Long.parseLong(productIdField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                try {
                    orderController.addOrder(clientId, productId, quantity);
                    showResultDialog("Order added successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog(ex.getMessage(), "Error");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(clientIdLabel);
        panel.add(clientIdField);
        panel.add(productIdLabel);
        panel.add(productIdField);
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