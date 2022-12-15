package com.mdma.orderservice.Services;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getSpecificOrder(String orderId){
        return orderRepository.findOrderById(orderId);
    }

    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> postOrder(Order order) {
        order.setOrderStatus(order.getOrderStatus().toLowerCase());
        if(orderRepository.save(order) == order) {
            return new ResponseEntity<String>("Order has been saved", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Order hasn't been saved", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Boolean> deleteOrder(String id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> getAllOrdersFromTable(String restaurantId, String tableId) {
        List<Order> orders = orderRepository.findOrdersByRestaurantIdAndTableId(restaurantId, tableId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> getAllOrdersFromRestaurant(String restaurantId) {

        List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);

        if (!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> updateOrder(String orderId, Order order){
        order.setId(orderId);
        if(orderRepository.findById(orderId).isPresent()){
            if(orderRepository.save(order) == order){
                return new ResponseEntity<>("Order has been updated", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("Order failed to updated", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>("Order hasn't been updated: Order not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Order>> GetAllReadyOrdersFromRestaurant(String restaurantId, String orderStatus) {
        return new ResponseEntity<List<Order>>(orderRepository.findOrdersByRestaurantIdAndOrderStatus(restaurantId,orderStatus),HttpStatus.OK);
    }
}
