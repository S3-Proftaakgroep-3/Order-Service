package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> FetchAllProducts() {
        return orderService.GetAllOrders();
    }

    @GetMapping("/all/{restaurantId}/{orderStatus}")
    public ResponseEntity<List<Order>> getAllReadyOrdersFromRestaurant(@PathVariable String restaurantId, @PathVariable String orderStatus){ return orderService.GetAllReadyOrdersFromRestaurant(restaurantId,orderStatus);}

    @PutMapping("/update/{Id}")
    public ResponseEntity<String> UpdateOrder(@PathVariable String Id, @RequestBody Order order){
        return orderService.updateOrder(Id, order);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        return orderService.postOrder(order);
    }

    @GetMapping("/restaurantId/{restaurantId}/tableId/{tableId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId, @PathVariable String tableId) {
        return orderService.GetAllOrdersFromTable(restaurantId, tableId);
    }

    @GetMapping("/{restaurantId}/all")
    public ResponseEntity<List<Order>> getAllOrdersFromRestaurant(@PathVariable String restaurantId){
        return orderService.GetAllOrdersFromRestaurant(restaurantId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam String id)
    {
        return orderService.deleteOrder(id);
    }

}
