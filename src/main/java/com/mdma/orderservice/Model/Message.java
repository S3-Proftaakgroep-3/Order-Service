package com.mdma.orderservice.Model;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
public class Message {
    public SseEmitter emitter;
    public String restaurantId;
    public String status;

    public Message(SseEmitter emitter, String restaurantId, String status) {
        this.emitter = emitter;
        this.restaurantId = restaurantId;
        this.status = status;
    }
}
