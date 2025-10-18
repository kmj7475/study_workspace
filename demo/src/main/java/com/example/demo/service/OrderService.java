package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;


public interface OrderService {

    // 단건 조회
    Optional<Order> findById(Long orderNo);

    // 회원별 주문 조회 (페이징)
    Page<OrderDto.Response> findOrdersByMember(Long memberNo, Pageable pageable);

    // 등록 (Order + OrderDetails 포함)
    Order createOrder(OrderDto.CreateRequest dto);

    // 수정 (Order + OrderDetails 포함)
    Order updateOrder(Long orderNo, OrderDto.UpdateRequest dto);

    // 삭제 (Order + OrderDetails 포함)
    void deleteOrder(Long orderNo);
}