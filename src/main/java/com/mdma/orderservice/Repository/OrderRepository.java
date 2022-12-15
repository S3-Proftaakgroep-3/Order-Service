package com.mdma.orderservice.Repository;

import com.mdma.orderservice.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrdersByRestaurantIdAndTableId(String restaurantId, String tableId);
    List<Order> findOrdersByRestaurantId(String restaurantId);
    List<Order> findOrdersByRestaurantIdAndOrderStatus(String restaurantId, String orderStatus);
    Order findOrderById(String orderId);

}
