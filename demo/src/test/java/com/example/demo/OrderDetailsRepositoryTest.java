package com.example.demo;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderDetailsRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Test
    @Transactional
    @Commit
    public void insertOrderWithDetails() {
        Order order = new Order();
        order.setReceiverName("테스터");
        order.setReceiverAddr("서울시 강남구");
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderStatus("READY");
        order.setOrderTotal(10000L);
        order.setUsedPoint(0L);
        order.setPaymentTotal(10000L);
        order.setPhone("01012345678");
        order.setMemberNo(1L);

        OrderDetails details1 = new OrderDetails();
        details1.setBookNo(101L);
        details1.setPurchaseQuantity(2L);
        details1.setPurchasePrice(5000L);
        details1.setPurchaseAmount(10000L);
        details1.setOrder(order);

        order.setOrderDetailsList(Arrays.asList(details1));

        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getOrderNo());
        assertEquals(1, savedOrder.getOrderDetailsList().size());
        OrderDetails savedDetails = savedOrder.getOrderDetailsList().get(0);
        assertEquals(101L, savedDetails.getBookNo());
    }
}
