package com.mdma.orderservice.Services;
import com.mdma.orderservice.Model.Message;
import com.mdma.orderservice.Model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SseEmitterService {
    private final List<Message> subscribers = new ArrayList<>();

    public SseEmitter subscribe(String restaurantId, String status) {
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

        subscribers.add(new Message(sseEmitter, restaurantId, status));
        return sseEmitter;
    }

    public void SendMessage(String restaurantId, List<Order> orders) {
        List<Integer> indexesToRemove = new ArrayList<>();
        for (int i = 0; i < subscribers.size(); i++) {
            try {
                if (Objects.equals(restaurantId, orders.get(0).getRestaurantId())) {
                    if (Objects.equals(restaurantId, subscribers.get(i).getRestaurantId())) {
                        int finalI = i;
                        List<Order> filteredOrders = orders.stream().filter(x -> Objects.equals(x.getOrderStatus(), subscribers.get(finalI).getStatus())).toList();
                        subscribers.get(i).emitter.send(SseEmitter.event().name("Latest's Orders").data(filteredOrders));
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
}
