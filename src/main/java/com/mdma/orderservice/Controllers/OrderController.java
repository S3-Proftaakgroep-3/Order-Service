package com.mdma.orderservice.Controllers;

import com.mdma.orderservice.Model.Message;
import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    public List<Message> subscribers;

    @GetMapping(value = "/subscribe/{restaurantId}", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@PathVariable String restaurantId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sseEmitter.onCompletion(() -> {
            int index = -1;
            for (int i = 0; i < subscribers.size(); i++) {
                if (subscribers.get(i).emitter == sseEmitter) {
                    index = i;
                }
            }
            if (index != -1) {
                subscribers.remove(index);
            }
        });

        subscribers.add(new Message(sseEmitter, restaurantId));
        return sseEmitter;
    }

    private void SendMessage(String restaurantId, List<Order> orders) {
        List<Integer> indexesToRemove = new ArrayList<>();
        for (int i = 0; i < subscribers.size(); i++) {
            try {
                if (Objects.equals(restaurantId, orders.get(0).getRestaurantId())) {
                    if (Objects.equals(restaurantId, subscribers.get(i).getRestaurantId())) {
                        subscribers.get(i).emitter.send(SseEmitter.event().name("Latest's Orders").data(orders));
                    }
                }
            } catch (IOException e) {
                indexesToRemove.add(i);
            }
        }

        for (int i = indexesToRemove.size() -1; i > 0 ; i--) {
            subscribers.remove(indexesToRemove.get(i));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> FetchAllProducts() {
        return orderService.GetAllOrders();
    }

    @GetMapping("/all/{restaurantId}/{orderStatus}")
    public ResponseEntity<List<Order>> getAllReadyOrdersFromRestaurant(@PathVariable String restaurantId, @PathVariable String orderStatus){
        return orderService.GetAllReadyOrdersFromRestaurant(restaurantId,orderStatus);
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
