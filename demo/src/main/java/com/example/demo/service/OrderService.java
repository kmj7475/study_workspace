package com.example.demo.service;

import com.example.demo.domain.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface OrderService {

  // 등록
  Order createOrder(Order order);

  // 수정
  Order updateOrder(Long orderNo, Order order);

  // 삭제
  void deleteOrder(Long orderNo);

  // 단건 조회
  Optional<Order> findById(Long orderNo);

  // 회원별 주문내역 조회 (페이징 X)
  List<Order> findByMemberNo(Long memberNo);

  // 관리자용 전체 조회 (날짜 + 회원번호 조건)
  Page<Order> searchOrders(Long memberNo, LocalDate startDate, LocalDate endDate, Pageable pageable);
}