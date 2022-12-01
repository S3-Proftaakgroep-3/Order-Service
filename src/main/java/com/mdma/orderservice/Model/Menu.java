package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Menu {
    @Id
    private String id;
    private List<Product> products;

    public Menu(List<Product> products) {
        this.products = products;
    }
}
