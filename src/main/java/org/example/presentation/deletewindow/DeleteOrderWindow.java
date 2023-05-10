package org.example.presentation.deletewindow;

import org.example.controller.OrderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteOrderWindow {
    private final OrderController orderController;
    private JFrame frame;
    private JTextField orderIdField;

    public DeleteOrderWindow(final OrderController orderController) {
        this.orderController = orderController;

        frame = new JFrame("Delete Order");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long orderId = Long.parseLong(orderIdField.getText());

                try {
                    orderController.deleteOrder(orderId);
                    showResultDialog("Order deleted successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog("Error deleting order.", "Error");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(orderIdLabel);
        panel.add(orderIdField);
        panel.add(new JLabel());
        panel.add(deleteButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showResultDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}