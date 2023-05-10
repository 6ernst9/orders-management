package org.example.presentation.updatewindow;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateOrderWindow {
    private final OrderController orderController;
    private JFrame frame;
    private JTextField orderIdField, clientIdField, productIdField, quantityField, priceField;

    public UpdateOrderWindow(final OrderController orderController) {
        this.orderController = orderController;

        frame = new JFrame("Update Order");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdField = new JTextField(20);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField(20);

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(20);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(20);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long orderId = Long.parseLong(orderIdField.getText());
                long clientId = Long.parseLong(clientIdField.getText());
                long productId = Long.parseLong(productIdField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                try {
                    orderController.updateOrder(orderId, clientId,  productId, quantity );
                    showResultDialog("Order updated successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog("Error updating order.", "Error");
                }
            }
        });
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(orderIdLabel);
        panel.add(orderIdField);
        panel.add(clientIdLabel);
        panel.add(clientIdField);
        panel.add(productIdLabel);
        panel.add(productIdField);
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