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
    private UUID id;
    private UUID tableId;
    private List<Product> productList;
    private Double orderAmount;
    private OrderStatus orderStatus;
}
