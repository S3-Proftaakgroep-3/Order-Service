package com.mdma.orderservice.Repository;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
