package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  // 주문 생성
  @Transactional
  public Order createOrder(Order order) {
    return orderRepository.save(order);
  }

  // 주문 조회
  public Order findById(Long orderNo) {
    return orderRepository.findById(orderNo)
        .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
  }

  // 모든 주문 조회
  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  // 주문 수정
  @Transactional
  public Order updateOrder(Order order) {
    Order existingOrder = findById(order.getOrderNo());
    // 필요한 필드 업데이트
    return orderRepository.save(existingOrder);
  }

  // 주문 삭제
  @Transactional
  public void deleteOrder(Long orderNo) {
    orderRepository.deleteById(orderNo);
  }
}