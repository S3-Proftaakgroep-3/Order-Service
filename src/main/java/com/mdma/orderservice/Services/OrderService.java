package com.mdma.orderservice.Services;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import com.mdma.orderservice.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public ResponseEntity<List<Order>> GetAllOrders(){
        return new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> postOrder(Order order) {
        if(orderRepository.save(order) == order) {
            return new ResponseEntity<String>("Order has been saved", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Order hasn't been saved", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Boolean> deleteOrder(String id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> GetAllOrdersFromTable(String restaurantId, String tableId) {
        List<Order> orders = orderRepository.findOrdersByRestaurantIdAndTableId(restaurantId, tableId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
