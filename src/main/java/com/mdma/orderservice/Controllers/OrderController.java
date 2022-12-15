package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Services.OrderService;
import com.mdma.orderservice.Services.SseEmitterService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SseEmitterService emitterService;


    @GetMapping(value = "/subscribe/{restaurantId}/status/{status}", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@PathVariable String restaurantId, @PathVariable String status) {
        return emitterService.subscribe(restaurantId, status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> FetchAllProducts() {
        return orderService.getAllOrders();
    }

    @GetMapping("/all/{restaurantId}/{orderStatus}")
    public ResponseEntity<List<Order>> getAllReadyOrdersFromRestaurant(@PathVariable String restaurantId, @PathVariable String orderStatus){
        orderStatus = orderStatus.toLowerCase();
        return orderService.GetAllReadyOrdersFromRestaurant(restaurantId,orderStatus);
    }

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId)
    {
        return orderService.getAllOrdersFromRestaurant(restaurantId);
    }

    @PostMapping("/create")
    public ResponseEntity<String>  createOrder(@RequestBody Order order) {
        ResponseEntity<String> newOrder = orderService.postOrder(order);
        ResponseEntity<List<Order>> orders = getAllOrdersFromRestaurant(order.getRestaurantId());
        emitterService.SendMessage(order.getRestaurantId(), orders.getBody());
        return newOrder;
    }

    @GetMapping("/restaurantId/{restaurantId}/tableId/{tableId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId, @PathVariable String tableId) {
        return orderService.getAllOrdersFromTable(restaurantId, tableId);
    }

    @GetMapping("/{restaurantId}/all")
    public ResponseEntity<List<Order>> getAllOrdersFromRestaurant(@PathVariable String restaurantId){
        return orderService.getAllOrdersFromRestaurant(restaurantId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestParam String id)
    {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> UpdateOrder(@RequestBody Order order){
        if (Objects.equals(order.getOrderStatus(), "preparing")) {
            order.setOrderStatus("ready");
        } else {
            order.setOrderStatus("received");
        }

        ResponseEntity<String> newOrder = orderService.updateOrder(order);
        ResponseEntity<List<Order>> orders = getAllOrdersFromRestaurant(order.getRestaurantId());
        emitterService.SendMessage(order.getRestaurantId(), orders.getBody());

        return newOrder;
    }
}
