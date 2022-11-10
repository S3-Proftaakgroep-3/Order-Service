package controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

import com.mdma.orderservice.Model.Order;
import com.mdma.orderservice.Model.Product;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mdma.orderservice.Repository.OrderRepository;
import com.mdma.orderservice.Repository.RestaurantRepository;
import com.mdma.orderservice.Services.OrderService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderService underTest;

    @BeforeEach
    void setup() {
        underTest = new OrderService(orderRepository);
    }

    @Test
    void getAllOrders() {
        // when the desired action performed
        underTest.GetAllOrders();

        // then verify
        verify(orderRepository).findAll();
    }

    @Test
    void shouldPostOrder()
    {
        // for given input
        List<Product> productList = new ArrayList<>();
        Order order = new Order("Kaas123", "Kaastafel1", productList, 20.00, null);

        // when the desired action performed
        underTest.postOrder(order);


        //then verify
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();

        assertThat(capturedOrder).isEqualTo(order);
    }

    @Test
    void shouldDeleteOrder() {
        // for given input
        final String id = "Kaas123";

        // when the desired action performed
        underTest.deleteOrder(id);

        //then verify
        verify(orderRepository).deleteById(id);
    }

    @Test
    void shouldGetAllOrdersFromTable()
    {
        // when the desired action performed
        underTest.GetAllOrdersFromTable("Kaas123", "Kaastafel123");

        //then verify
        verify(orderRepository).findOrdersByRestaurantIdAndTableId("Kaas123", "Kaastafel123");
    }

}
