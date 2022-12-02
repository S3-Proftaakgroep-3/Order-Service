package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
public class Message {
    public SseEmitter emitter;
    public String restaurantId;

    public Message(SseEmitter emitter, String restaurantId) {
        this.emitter = emitter;
        this.restaurantId = restaurantId;
    }
}
