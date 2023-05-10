package org.example.model;

import java.io.FileWriter;
import java.io.IOException;

public class BillGenerator {

    public static void generateBill(Bill bill, String name, float price) {
        String fileName = "bill" + bill.getId() + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName);

            writer.write("*******************************************\n");
            writer.write("RECEIPT\n");
            writer.write("*******************************************\n");
            writer.write("ID: " + bill.getId() + "\n");
            writer.write("Date: " + bill.getDate() + "\n");
            writer.write("Time: " + bill.getTime() + "\n");
            writer.write("--------------------------------------------\n");
            writer.write("Client ID: " + bill.getClientId() + "\n");
            writer.write(bill.getQuantity() + " x " + name +"("+ bill.getProductID()+")..................$" + price+"\n");
            writer.write("--------------------------------------------\n");
            writer.write("TOTAL AMOUNT: .....................$" + bill.getPrice() + "$" + "\n");
            writer.write("--------------------------------------------\n\n");
            writer.write("***************** THANK YOU *******************\n");

            writer.close();

            System.out.println("Bill generated successfully.");
        } catch (IOException e) {
            System.out.println("Error while generating bill: " + e.getMessage());
        }
    }
}
