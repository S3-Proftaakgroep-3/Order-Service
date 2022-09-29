package com.mdma.orderservice.Repository;

import com.mdma.orderservice.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
