package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id @GeneratedValue( strategy = GenerationType.AUTO)
    private long Id;
    @Column(name = "datee")
    private String date;
    @Column(name = "time")
    private String time;

    @Column(name = "clientId")
    private long clientId;
    @Column(name = "productId")
    private long productID;
    @Column(name = "price")
    private float price;
    @Column(name = "quantity")
    private int quantity;

    public Bill(String date, String time, long clientId, long productID, float price, int quantity) {
        this.date = date;
        this.time = time;
        this.clientId = clientId;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "Id=" + Id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", productID=" + productID +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
