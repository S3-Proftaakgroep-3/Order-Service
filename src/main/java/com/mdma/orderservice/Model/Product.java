package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Product {
    private String name;
    private String message;
    private Double price;
    private List<ExtraOptions> extras;
    private String size;
    private int quantity;

    public Product(String name, String message, Double price, String size, int quantity, List<ExtraOptions> extras) {
        this.name = name;
        this.message = message;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.extras = extras;
    }
}
