package org.example.controller;

import org.example.business_logic.ClientService;
import org.example.model.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    public void addClient(String name, String email, int age) throws SQLException {
        Client client = new Client(name, email, age);
        clientService.addClient(client);
    }

    public void updateClient(long id, String name, String email, int age) throws SQLException {
        Client client = new Client(id, name, email, age);
        clientService.updateClient(client);
    }

    public void deleteClient(Long id) throws SQLException {
        clientService.deleteClient(id);
    }

    public ArrayList<Client> getClients() throws SQLException {
        return clientService.findAllClients();
    }

    public Client getClientById(long id) throws SQLException {
        return clientService.findClientById(id);
    }
    public ClientService getClientService(){
        return clientService;
    }
}
