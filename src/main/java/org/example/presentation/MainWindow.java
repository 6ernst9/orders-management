package org.example.presentation;

import org.example.controller.ClientController;
import org.example.controller.OrderController;
import org.example.controller.ProductController;
import org.example.presentation.addwindow.AddClientWindow;
import org.example.presentation.addwindow.AddOrderWindow;
import org.example.presentation.addwindow.AddProductWindow;
import org.example.presentation.deletewindow.DeleteClientWindow;
import org.example.presentation.deletewindow.DeleteOrderWindow;
import org.example.presentation.deletewindow.DeleteProductWindow;
import org.example.presentation.updatewindow.UpdateClientWindow;
import org.example.presentation.updatewindow.UpdateOrderWindow;
import org.example.presentation.updatewindow.UpdateProductWindow;
import org.example.presentation.viewwindow.ViewClientsWindow;
import org.example.presentation.viewwindow.ViewOrdersWindow;
import org.example.presentation.viewwindow.ViewProductsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow {
    private final ClientController clientController;
    private final ProductController productController;
    private final OrderController orderController;
    private JFrame frame;
    private JButton addClientButton, deleteClientButton, updateClientButton, viewClientButton;
    private JButton addProductButton, deleteProductButton, updateProductButton, viewProductButton;
    private JButton addOrderButton, deleteOrderButton, updateOrderButton, viewOrderButton;

    public MainWindow(ClientController clientController, ProductController productController, OrderController orderController) {
        this.clientController = clientController;
        this.productController = productController;
        this.orderController = orderController;

        frame = new JFrame("Order Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addClientButton = createButton("Add Client");
        deleteClientButton = createButton("Delete Client");
        updateClientButton = createButton("Update Client");
        viewClientButton = createButton("View Clients");

        addProductButton = createButton("Add Product");
        deleteProductButton = createButton("Delete Product");
        updateProductButton = createButton("Update Product");
        viewProductButton = createButton("View Products");

        addOrderButton = createButton("Add Order");
        deleteOrderButton = createButton("Delete Order");
        updateOrderButton = createButton("Update Order");
        viewOrderButton = createButton("View Orders");

        mainPanel.add(addClientButton);
        mainPanel.add(deleteClientButton);
        mainPanel.add(updateClientButton);
        mainPanel.add(viewClientButton);
        mainPanel.add(addProductButton);
        mainPanel.add(deleteProductButton);
        mainPanel.add(updateProductButton);
        mainPanel.add(viewProductButton);
        mainPanel.add(addOrderButton);
        mainPanel.add(deleteOrderButton);
        mainPanel.add(updateOrderButton);
        mainPanel.add(viewOrderButton);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton createButton(final String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButtonAction(label);
            }
        });
        return button;
    }

    private void handleButtonAction(String label) {
        switch (label) {
            case "Add Client":
                AddClientWindow addClientWindow = new AddClientWindow(clientController);
                break;
            case "Delete Client":
                DeleteClientWindow deleteClientWindow = new DeleteClientWindow(clientController);
                break;
            case "Update Client":
                UpdateClientWindow updateClientWindow = new UpdateClientWindow(clientController);
                break;
            case "View Clients":
                ViewClientsWindow viewClientsWindow = new ViewClientsWindow(clientController);
                break;
            case "Add Product":
                AddProductWindow addProductWindow = new AddProductWindow(productController);
                break;
            case "Delete Product":
                DeleteProductWindow deleteProductWindow = new DeleteProductWindow(productController);
                break;
            case "Update Product":
                UpdateProductWindow updateProductWindow = new UpdateProductWindow(productController);
                break;
            case "View Products":
                ViewProductsWindow viewProductsWindow = new ViewProductsWindow(productController);
                break;
            case "Add Order":
                AddOrderWindow addOrderWindow = new AddOrderWindow(orderController);
                break;
            case "Delete Order":
                DeleteOrderWindow deleteOrderWindow = new DeleteOrderWindow(orderController);
                break;
            case "Update Order":
                UpdateOrderWindow updateOrderWindow = new UpdateOrderWindow(orderController);
                break;
            case "View Orders":
                ViewOrdersWindow viewOrdersWindow = new ViewOrdersWindow(orderController);
                break;
        }
    }
}
