package org.example.controller;

import org.example.business_logic.ProductService;
import org.example.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    public void addProduct(String name, float price, int quantity) throws SQLException {
        Product product = new Product(name,price, quantity);
        productService.addProduct(product);
    }

    public void updateProduct(long id, String name, float price, int quantity) throws SQLException {
        Product product = new Product(id, name, price, quantity);
        productService.updateProduct(product);
    }

    public void deleteProduct(Long id) throws SQLException {
        productService.deleteProduct(id);
    }

    public ArrayList<Product> getProducts() throws SQLException {
        return productService.findAllProducts();
    }

    public Product getProductById(long id) throws SQLException {
        return productService.findProductById(id);
    }

    public ProductService getProductService(){
        return productService;
    }
}
