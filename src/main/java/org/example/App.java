package org.example;

import org.example.business_logic.ClientService;
import org.example.business_logic.OrderService;
import org.example.business_logic.ProductService;
import org.example.controller.ClientController;
import org.example.controller.OrderController;
import org.example.controller.ProductController;
import org.example.dao.BillRepository;
import org.example.dao.ClientRepository;
import org.example.dao.OrderRepository;
import org.example.dao.ProductRepository;
import org.example.dbconnection.DBConnection;
import org.example.presentation.MainWindow;

public class App
{
    public static void main( String[] args )
    {
        ClientRepository clientRepository = new ClientRepository(DBConnection.getConnection());
        ProductRepository productRepository = new ProductRepository(DBConnection.getConnection());
        OrderRepository orderRepository = new OrderRepository(DBConnection.getConnection());
        BillRepository billRepository = new BillRepository(DBConnection.getConnection(), productRepository);

        ClientService clientService = new ClientService(clientRepository);
        ProductService productService = new ProductService(productRepository, orderRepository);
        OrderService orderService = new OrderService(orderRepository, productRepository, clientRepository, billRepository);

        ClientController clientController = new ClientController(clientService);
        ProductController productController = new ProductController(productService);
        OrderController orderController = new OrderController(orderService);

        MainWindow mainWindow = new MainWindow(clientController, productController, orderController);
    }
}
