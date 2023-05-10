package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "quantity")
    private int quantity;

    public Product(String name, float price, int quantity) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public String toString(){
        return Id + ", " + "'" + this.name + "'" + ", " + this.price;
    }
}
