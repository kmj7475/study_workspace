package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.MemberService;
import com.example.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final MemberService memberService;

  // 주문 생성
  @PostMapping
  public ResponseEntity<OrderDto.Response> createOrder(@RequestBody OrderDto.CreateRequest request) {
    Member member = memberService.findById(request.getMemberNo());

    Order order = new Order();
    order.setMember(member);
    order.setReceiverName(request.getReceiverName());
    order.setReceiverAddr(request.getReceiverAddr());
    order.setPhone(request.getPhone());
    order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
    order.setOrderStatus("ORDER");
    order.setOrderTotal(0L);
    order.setUsedPoint(0L);
    order.setPaymentTotal(0L);

    Order savedOrder = orderService.createOrder(order);
    return ResponseEntity.ok(convertToResponse(savedOrder));
  }

  // 주문 목록 조회
  @GetMapping
  public ResponseEntity<List<OrderDto.Response>> getAllOrders() {
    List<Order> orders = orderService.findAll();
    List<OrderDto.Response> responses = orders.stream()
        .map(this::convertToResponse)
        .toList();
    return ResponseEntity.ok(responses);
  }

  // 주문 조회
  @GetMapping("/{orderNo}")
  public ResponseEntity<OrderDto.Response> getOrder(@PathVariable Long orderNo) {
    Order order = orderService.findById(orderNo);
    return ResponseEntity.ok(convertToResponse(order));
  }

  // 주문 삭제
  @DeleteMapping("/{orderNo}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long orderNo) {
    orderService.deleteOrder(orderNo);
    return ResponseEntity.ok().build();
  }

  // Entity -> Response DTO 변환
  private OrderDto.Response convertToResponse(Order order) {
    OrderDto.Response response = new OrderDto.Response();
    response.setOrderNo(order.getOrderNo());
    response.setMemberNo(order.getMember().getMemberNo());
    response.setReceiverName(order.getReceiverName());
    response.setReceiverAddr(order.getReceiverAddr());
    response.setPhone(order.getPhone());
    response.setTotalAmount(order.getOrderTotal());
    response.setOrderStatus(order.getOrderStatus());
    // 총 금액 등 필요한 정보 설정
    return response;
  }
}