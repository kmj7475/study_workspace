package com.example.demo;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    public void testAddDetail() {
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

        OrderDetails details = new OrderDetails();
        details.setBookNo(101L);
        details.setPurchaseQuantity(2L);
        details.setPurchasePrice(5000L);
        details.setPurchaseAmount(10000L);

        order.addDetail(details);

        assertEquals(1, order.getOrderDetailsList().size());
        assertSame(order, details.getOrder());
        assertEquals(101L, order.getOrderDetailsList().get(0).getBookNo());
    }
}
