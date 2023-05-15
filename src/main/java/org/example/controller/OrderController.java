package org.example.controller;

import org.example.business_logic.OrderService;
import org.example.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    public void addOrder(long clientId, long productId, int quantity) throws SQLException {
        Order order = new Order(clientId, productId, quantity);
        orderService.addOrder(order);
    }

    public void updateOrder(long id, long clientId, long productId, int quantity) throws SQLException {
        Order order = new Order(id, clientId, productId, quantity);
        orderService.updateOrder(order);
    }

    public void deleteOrder(Long id) throws SQLException {
        orderService.deleteOrder(id);
    }

    public ArrayList<Order> getOrders() throws SQLException {
        return orderService.findAllOrders();
    }

    public Order getOrderById(long id) throws SQLException {
        return orderService.findOrderById(id);
    }

    public ArrayList<Order> getOrdersByClientId(long id) throws SQLException {
        return orderService.findOrdersByClientId(id);
    }

    public ArrayList<Order> getOrdersByProductId(long id) throws SQLException {
        return orderService.findOrdersByProductId(id);
    }

    public OrderService getOrderService(){
        return orderService;
    }
}
