package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    private String name;
    private String message;
    private Double price;
    private String size;
    private int quantity;

    public Product(String name, String message, Double price, String size, int quantity) {
        this.name = name;
        this.message = message;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }
}
