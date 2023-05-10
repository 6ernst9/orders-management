package org.example.presentation.deletewindow;

import org.example.controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DeleteProductWindow {
    private final ProductController productController;
    private JFrame frame;
    private JTextField productIdField;

    public DeleteProductWindow(final ProductController productController) {
        this.productController = productController;

        frame = new JFrame("Delete Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel productIdLabel = new JLabel("Product ID:");
        productIdField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long productId = Long.parseLong(productIdField.getText());

                try {
                    productController.deleteProduct(productId);
                    showResultDialog("Product deleted successfully!", "Success");
                    frame.dispose();
                } catch (SQLException ex) {
                    showResultDialog("Error deleting product.", "Error");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(productIdLabel);
        panel.add(productIdField);
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
