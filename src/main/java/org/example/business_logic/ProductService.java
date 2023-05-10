package org.example.business_logic;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.example.dao.OrderRepository;
import org.example.dao.ProductRepository;
import org.example.model.Order;
import org.example.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public ProductService(ProductRepository productRepository, OrderRepository orderRepository){
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }
    public void addProduct(Product product) throws SQLException {
        if(!product.getName().contains(" "))
            throw new SQLException("Invalid name. Please try again.");
        if(product.getPrice()<10)
            throw new SQLException("Invalid price. Please try again.");
        if(product.getQuantity()<10)
            throw new SQLException("Invalid quantity. Please try again.");

        productRepository.addProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
        if(!product.getName().contains(" "))
            throw new SQLException("Invalid name. Please try again.");
        if(product.getPrice()<10)
            throw new SQLException("Invalid price. Please try again.");
        if(product.getQuantity()<10)
            throw new SQLException("Invalid quantity. Please try again.");
        ArrayList<Order> orders = orderRepository.findOrdersByProductId(product.getId());
        Product product1 = productRepository.findProductById(product.getId());

        int quantity=0;
        for(Order order: orders){
            if(order.getProductID() == product.getId()){
                quantity += order.getQuantity();
            }
        }
        if(quantity > product1.getQuantity())
            throw new SQLException("Quantity not enough. Please try again.");

        for(Order order: orders){
            if(order.getProductID() == product.getId()){
                product.setQuantity( product.getQuantity() - order.getQuantity());
            }
        }
        productRepository.updateProduct(product);
        if(product.getPrice() != product1.getPrice()){
            for(Order order: orders){
                if(order.getProductID() == product.getId() ){
                    order.setPrice(order.getQuantity() * product.getPrice());
                    orderRepository.updateOrder(order);
                }
            }
        }
    }
    public void deleteProduct(Long id) throws SQLException {
        if(id<1)  throw new SQLException("Invalid id. Please try again.");

        productRepository.deleteProduct(id);
        ArrayList<Order> orders = orderRepository.findAllOrders();
        for(Order order : orders){
            if(order.getProductID() == id){
                orderRepository.deleteOrder(order.getId());
            }
        }
    }

    public ArrayList<Product> findAllProducts() throws SQLException {
        return productRepository.findAllProducts();
    }

    public Product findProductById(Long id) throws SQLException {
        if(id<1) throw new SQLException("Invalid id. Please try again.");
        return productRepository.findProductById(id);
    }
}
