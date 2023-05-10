package org.example.presentation.deletewindow;

import org.example.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DeleteClientWindow {
    private final ClientController clientController;
    private JFrame frame;
    private JTextField clientIdField;

    public DeleteClientWindow(final ClientController clientController) {
        this.clientController = clientController;

        frame = new JFrame("Delete Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long clientId = Long.parseLong(clientIdField.getText());

                try {
                    clientController.deleteClient(clientId);
                    showResultDialog("Client deleted successfully!", "Success");
                }
                catch (SQLException ex) {
                    showResultDialog(ex.getMessage(), "Error");
                }

                frame.dispose();
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(clientIdLabel);
        panel.add(clientIdField);
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
