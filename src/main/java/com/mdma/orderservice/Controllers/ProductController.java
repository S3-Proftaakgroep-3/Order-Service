package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> FetchAllProducts() {
        return productService.GetAllProducts();
    }
}
