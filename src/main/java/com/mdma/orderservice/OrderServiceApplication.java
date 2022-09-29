package com.mdma.orderservice;

import com.mdma.orderservice.Enum.ProductSize;
import com.mdma.orderservice.Model.Menu;
import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Model.Restaurant;
import com.mdma.orderservice.Repository.ProductRepository;
import com.mdma.orderservice.Repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner(RestaurantRepository repository) {
//        return args ->  {
//            Restaurant restaurant = new Restaurant(
//                "Bread de bakker",
//                new Menu(
//                    new ArrayList<Product>(
//                        Arrays.asList(
//                            new Product("Kaas soep", "Lekker kaas soepje", 4.50, ProductSize.Small, true),
//                            new Product("Kaas soep", "Lekker kaas soepje", 4.50, ProductSize.Small, true)
//                        )
//                    )
//                )
//            );
//
//            repository.insert(restaurant);
//        };
//    }

}
