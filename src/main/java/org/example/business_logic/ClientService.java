package org.example.business_logic;

import org.example.dao.ClientRepository;
import org.example.model.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    public void addClient(Client client) throws SQLException {
        if(!client.getName().contains(" "))
            throw new SQLException("Name invalid. Please try again.");
        if(!client.getEmail().contains("@") || !client.getEmail().contains("."))
            throw new SQLException("Invalid email. Please try again.");
        if(client.getAge()<18)
            throw new SQLException("Age must be over 18!");

        clientRepository.addClient(client);
    }

    public void updateClient(Client client) throws SQLException {
        if(!client.getName().contains(" "))
            throw new SQLException("Name invalid. Please try again.");
        if(!client.getEmail().contains("@") || !client.getEmail().contains("."))
            throw new SQLException("Invalid email. Please try again.");
        if(client.getAge()<18)
            throw new SQLException("Age must be over 18!");

        clientRepository.updateClient(client);
    }

    public void deleteClient(Long id) throws SQLException {
        if(id<1) throw new SQLException("Invalid id. Please try again.");
        clientRepository.deleteClient(id);
    }

    public ArrayList<Client> findAllClients() throws SQLException {
        return clientRepository.findAllClients();
    }

    public Client findClientById(Long id) throws SQLException {
        if(id<1) throw new SQLException("Invalid id. Please try again.");
        return clientRepository.findClientById(id);
    }
}
