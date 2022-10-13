package com.mdma.orderservice;

import com.mdma.orderservice.Enum.OrderStatus;
import com.mdma.orderservice.Enum.ProductSize;
import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(OrderRepository repository) {
        return args ->  {
            Order order = new Order(
                    "NewOrder1",
                    "1",
                    new ArrayList<Product>(
                            Arrays.asList(
                                    new Product("Kaas", "Lekker stukje kaas", 5.50, ProductSize.Small, true),
                                    new Product("Kaas soep", "Lekker kaas soepje", 6.50, ProductSize.Small, true))

                    ),
                    12.00,
                OrderStatus.Received);

            repository.insert(order);
        };
    }

}
