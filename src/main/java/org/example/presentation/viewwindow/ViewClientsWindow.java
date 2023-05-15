package org.example.presentation.viewwindow;

import org.example.controller.ClientController;
import org.example.model.Client;
import org.example.presentation.TableUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewClientsWindow {
    private final ClientController clientController;
    private JFrame frame;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public ViewClientsWindow(final ClientController clientController) throws SQLException {
        this.clientController = clientController;

        frame = new JFrame("View Clients");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = TableUtil.createTable(clientController.getClientService().getClientRepository(), Client.class);
        clientTable = new JTable(tableModel);
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(clientTable);
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchId = searchField.getText();
                searchClientsById(searchId);
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

    private void searchClientsById(String textId) {
        tableModel.setRowCount(0);
        if(Objects.equals(textId, "") || Objects.equals(textId, " ")){
            getAllClients();
            return;
        }
        long id = Long.parseLong(textId);
        try {
            Client client = clientController.getClientById(id);
            if (client != null) {
                Object[] row = {client.getId(), client.getName(), client.getEmail(), client.getAge()};
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Client not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getAllClients(){
        ArrayList<Client> clientList = null;
        try {
            clientList = clientController.getClients();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        assert clientList != null;
        for (Client client : clientList) {
            Object[] row = {client.getId(), client.getName(), client.getEmail(), client.getAge()};
            tableModel.addRow(row);
        }
    }
}
