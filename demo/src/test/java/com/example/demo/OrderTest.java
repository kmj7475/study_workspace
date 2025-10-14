package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.repository.MemberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testAddDetail() {

        Optional<Member> member = memberRepository.findById(1L);
        Order order = new Order();
        order.setReceiverName("테스터");
        order.setReceiverAddr("서울시 강남구");
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderStatus("READY");
        order.setOrderTotal(10000L);
        order.setUsedPoint(0L);
        order.setPaymentTotal(10000L);
        order.setPhone("01012345678");
        order.setMember(member.get());

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
