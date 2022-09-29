package com.mdma.orderservice.Services;

import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> GetAllProducts() {
        return productRepository.findAll();
    }
}
