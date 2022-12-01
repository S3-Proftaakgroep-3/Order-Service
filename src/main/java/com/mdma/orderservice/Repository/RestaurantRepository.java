package com.mdma.orderservice.Repository;

import com.mdma.orderservice.Model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
}
