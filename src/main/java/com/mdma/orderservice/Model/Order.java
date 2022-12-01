package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class Order {
    @Id
    private String id;
    private String tableId;
    private String restaurantId;
    private List<Product> products;
    private String orderStatus;

    public Order(String id, String tableId, List<Product> products, String orderStatus) {
        this.id = id;
        this.tableId = tableId;
        this.products = products;
        this.orderStatus = orderStatus;
    }
}
