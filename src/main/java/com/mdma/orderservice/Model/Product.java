package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String size;
    private Boolean active;

    public Product(String name, String description, Double price, String size, Boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.active = active;
    }
}
