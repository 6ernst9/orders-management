package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "package")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id @GeneratedValue( strategy = GenerationType.AUTO)
    private long Id;
    @Column(name = "clientId")
    private long clientID;
    @Column(name = "productId")
    private long productID;
    @Column(name = "price")
    private float price;
    @Column(name = "quantity")
    private int quantity;

    public Order(long clientID, long productID, int quantity, float price) {
        this.clientID = clientID;
        this.quantity = quantity;
        this.productID = productID;
        this.price = price;
    }

    public Order(long id, long clientID, long productID, int quantity) {
        this.clientID = clientID;
        this.quantity = quantity;
        this.productID = productID;
        this.Id = id;
    }

    public Order(long clientID, long productID, int quantity) {
        this.clientID = clientID;
        this.quantity = quantity;
        this.productID = productID;
    }

    public String toString(){
        return Id + ", " + this.clientID + ", " + this.productID + ", " + this.quantity + ", " + this.price;
    }
}
