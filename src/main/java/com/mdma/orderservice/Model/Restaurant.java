package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Restaurant {
    @Id
    private String id;
    private String name;

    public Restaurant(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    private Menu menu;
}
