package com.mdma.orderservice.Repository;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrdersByRestaurantIdAndTableId(String restaurantId, String tableId);

}
