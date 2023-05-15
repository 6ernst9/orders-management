package org.example.business_logic;

import org.example.dao.BillRepository;
import org.example.dao.ClientRepository;
import org.example.dao.OrderRepository;
import org.example.dao.ProductRepository;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final BillRepository billRepository;
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        ClientRepository clientRepository, BillRepository billRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.billRepository = billRepository;
    }

    public void addOrder(Order order) throws SQLException {
        if(order.getQuantity() < 1)
            throw new SQLException("Invalid quantity. Please try again.");
        if(order.getClientID() < 0)
            throw new SQLException("Invalid client id. Please try again.");
        if(order.getProductID() < 0)
            throw new SQLException("Invalid product id. Please try again.");

        Product product = productRepository.findById(order.getProductID());
        if(product != null) order.setPrice(product.getPrice() * order.getQuantity());

        assert product != null;
        if(order.getQuantity() > product.getQuantity())
            throw new SQLException("Quantity not enough. Please try again.");

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dateString = currentDate.format(formatter1);
        String timeString = currentTime.format(formatter2);

        Bill bill = new Bill(dateString,timeString, order.getClientID(), order.getProductID(), order.getPrice(), order.getQuantity());
        orderRepository.add(order);

        Product newProduct = productRepository.findById(order.getProductID());
        newProduct.setQuantity(newProduct.getQuantity() - order.getQuantity());
        productRepository.update(newProduct);
        billRepository.add(bill);
    }

    public void updateOrder(Order order) throws SQLException {
        if(order.getQuantity() < 1)
            throw new SQLException("Invalid quantity. Please try again.");
        if(order.getClientID() < 0)
            throw new SQLException("Invalid client id. Please try again.");
        if(order.getProductID() < 0)
            throw new SQLException("Invalid product id. Please try again.");

        Product product = productRepository.findById(order.getProductID());
        if(product != null) order.setPrice(product.getPrice() * order.getQuantity());

        assert product != null;
        Order oldOrder = orderRepository.findById(order.getId());
        if(order.getQuantity() > product.getQuantity() + oldOrder.getQuantity())
            throw new SQLException("Quantity not enough. Please try again.");

        orderRepository.update(order);

        Product newProduct = productRepository.findById(order.getProductID());
        newProduct.setQuantity(newProduct.getQuantity() + oldOrder.getQuantity() - order.getQuantity());
        productRepository.update(newProduct);

    }

    public void deleteOrder(Long id) throws SQLException {
        if(id<1) throw new SQLException("Invalid id. Please try again.");
        Order oldOrder = orderRepository.findById(id);
        Product product = productRepository.findById(oldOrder.getProductID());
        int quantity = oldOrder.getQuantity();

        orderRepository.delete(id);
        product.setQuantity(product.getQuantity() + quantity);
        productRepository.update(product);

    }

    public ArrayList<Order> findOrdersByClientId(Long id) throws SQLException{
        if(id<1)  throw new SQLException("Invalid id. Please try again.");
        Client client = clientRepository.findById(id);
        if(client==null){
            throw new SQLException("No client associated with this id");
        }
        return orderRepository.findOrdersByClientId(id);
    }

    public ArrayList<Order> findOrdersByProductId(Long id) throws SQLException{
        if(id<1)  throw new SQLException("Invalid id. Please try again.");;
        Product product = productRepository.findById(id);
        if(product == null){
            throw new SQLException("No product associated with this id");
        }
        return orderRepository.findOrdersByProductId(id);
    }

    public ArrayList<Order> findAllOrders() throws SQLException {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) throws SQLException {
        if(id<1)  throw new SQLException("Invalid id. Please try again.");;
        return orderRepository.findById(id);
    }

    public OrderRepository getOrderRepository(){
        return orderRepository;
    }
}
