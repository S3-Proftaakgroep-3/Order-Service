package com.mdma.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
