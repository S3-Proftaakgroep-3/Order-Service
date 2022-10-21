package com.mdma.orderservice.Model;

import com.mdma.orderservice.Enum.OrderStatus;
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
    private List<Product> productList;
    private Double orderAmount;
    private OrderStatus orderStatus;

    public Order(String id, String tableId, List<Product> productList, Double orderAmount, OrderStatus orderStatus) {
        this.id = id;
        this.tableId = tableId;
        this.productList = productList;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
    }
}
