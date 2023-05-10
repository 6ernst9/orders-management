package org.example.presentation.updatewindow;

import org.example.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UpdateClientWindow {
    private final ClientController clientController;
    private JFrame frame;
    private JTextField clientIdField, nameField, emailField, ageField;

    public UpdateClientWindow(final ClientController clientController) {
        this.clientController = clientController;

        frame = new JFrame("Update Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdField = new JTextField(20);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long clientId = Long.parseLong(clientIdField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                try {
                    clientController.updateClient(clientId, name, email, age);
                    showResultDialog("Client updated successfully!", "Success");
                } catch (SQLException ex) {
                    showResultDialog(ex.getMessage(), "Error");
                }

                frame.dispose();
            }
        });

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(clientIdLabel);
        panel.add(clientIdField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(ageLabel);
        panel.add(ageField);
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

