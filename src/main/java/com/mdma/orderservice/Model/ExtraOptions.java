package com.mdma.orderservice.Model;

import lombok.Data;

@Data
public class ExtraOptions {

    private String name;
    private Double price;

    public ExtraOptions(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
