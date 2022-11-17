package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Services.OrderService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> FetchAllProducts() {
        return orderService.GetAllOrders();
    }

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId)
    {
        return orderService.GetAllOrdersFromTable()
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        return orderService.postOrder(order);
    }

    @GetMapping("/restaurantId/{restaurantId}/tableId/{tableId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId, @PathVariable String tableId) {
        return orderService.GetAllOrdersFromTable(restaurantId, tableId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam String id)
    {
        return orderService.deleteOrder(id);
    }

}
