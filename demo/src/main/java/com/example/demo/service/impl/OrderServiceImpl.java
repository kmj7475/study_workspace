package com.example.demo.service.impl;

import com.example.demo.domain.Order;

import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderNo, Order order) {
        Order existing = orderRepository.findById(orderNo)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다. orderNo=" + orderNo));

        existing.setOrderDate(order.getOrderDate());
        existing.setMember(order.getMember());
        existing.setOrderDetailsList(order.getOrderDetailsList());
        return orderRepository.save(existing);
    }

    @Override
    public void deleteOrder(Long orderNo) {
        orderRepository.deleteById(orderNo);
    }

    @Override
    public Optional<Order> findById(Long orderNo) {
        return orderRepository.findById(orderNo);
    }

    @Override
    public List<Order> findByMemberNo(Long memberNo) {
        return orderRepository.findByMember_MemberNo(memberNo);
    }

    // ✅ 관리자용 전체 조회 (페이징 포함)
    @Override
    public Page<Order> searchOrders(Long memberNo, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (memberNo != null && startDate != null && endDate != null) {
            return orderRepository.findByMember_MemberNoAndOrderDateBetween(memberNo, startDate, endDate, pageable);
        } else if (memberNo != null) {
            return orderRepository.findByMember_MemberNo(memberNo, pageable);
        } else if (startDate != null && endDate != null) {
            return orderRepository.findByOrderDateBetween(startDate, endDate, pageable);
        } else {
            return orderRepository.findAll(pageable);
        }
    }
}