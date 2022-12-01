package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Services.OrderService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    public List<SseEmitter> emitters;

    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sseEmitter.onCompletion(() -> {
            emitters.remove(sseEmitter);
        });

        emitters.add(sseEmitter);
        return sseEmitter;
    }

    private void SendMessage(String restaurantId, List<Order> orders) {
        for (int i = 0; i < emitters.size(); i++) {
            try {
                if (Objects.equals(restaurantId, orders.get(0).getRestaurantId())) {
                    emitters.get(i).send(SseEmitter.event().name("Latest's Orders").data(orders));
                }
            } catch (IOException e) {
                emitters.remove(emitters.get(i));
            }
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> FetchAllProducts() {
        return orderService.GetAllOrders();
    }

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity<List<Order>> getAllOrdersFromTable(@PathVariable String restaurantId)
    {
        return orderService.GetAllOrdersFromRestaurant(restaurantId);
    }

    @PostMapping("/create")
    public ResponseEntity<String>  createOrder(@RequestBody Order order) {
        ResponseEntity<String> newOrder = orderService.postOrder(order);
        ResponseEntity<List<Order>> orders = getAllOrdersFromRestaurant(order.getRestaurantId());
        SendMessage(order.getRestaurantId(), orders.getBody());
        return newOrder;
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
