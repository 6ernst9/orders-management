package org.example.presentation.addwindow;

import org.example.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AddClientWindow {
    private final ClientController clientController;
    private JFrame frame;
    private JTextField nameField, emailField, ageField;

    public AddClientWindow(final ClientController clientController) {
        this.clientController = clientController;

        frame = new JFrame("Add Client");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());

                try {
                    clientController.addClient(name, email, age);
                    showResultDialog("Client added successfully!", "Success");
                }
                catch (SQLException ex) {
                    showResultDialog(ex.getMessage(), "Error");
                }

                frame.dispose();
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(ageLabel);
        panel.add(ageField);
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
