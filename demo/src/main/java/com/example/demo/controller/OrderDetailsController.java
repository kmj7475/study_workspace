package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderDetailsService;
import com.example.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

  private final OrderDetailsService orderDetailsService;
  private final OrderService orderService;
  private final BookService bookService;

  // 주문 상세 생성
  @PostMapping
  public ResponseEntity<OrderDetailsDto.Response> createOrderDetails(
      @RequestBody OrderDetailsDto.CreateRequest request) {

    Order order = orderService.findById(request.getOrderNo());
    BookDto.Response book = bookService.getBookByBookNo(request.getBookNo().intValue()).orElse(null);
    if (book == null) {
      return ResponseEntity.badRequest().build();
    }

    OrderDetails orderDetails = new OrderDetails();
    orderDetails.setBookNo(request.getBookNo());
    orderDetails.setPurchaseQuantity(request.getPurchaseQuantity());
    orderDetails.setPurchasePrice(book.getPrice());
    orderDetails.setPurchaseAmount(book.getPrice() * request.getPurchaseQuantity());
    orderDetails.setOrder(order);

    OrderDetails savedOrderDetails = orderDetailsService.createOrderDetails(orderDetails);

    return ResponseEntity.ok(convertToResponse(savedOrderDetails));
  }

  // 주문별 상세 목록 조회
  @GetMapping("/order/{orderNo}")
  public ResponseEntity<List<OrderDetailsDto.Response>> getOrderDetailsByOrder(
      @PathVariable Long orderNo) {

    Order order = orderService.findById(orderNo);
    List<OrderDetails> orderDetailsList = orderDetailsService.findByOrder(order);

    List<OrderDetailsDto.Response> responses = orderDetailsList.stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());

    return ResponseEntity.ok(responses);
  }

  // 주문 상세 단건 조회
  @GetMapping("/{orderItemNo}")
  public ResponseEntity<OrderDetailsDto.Response> getOrderDetails(
      @PathVariable Long orderItemNo) {

    OrderDetails orderDetails = orderDetailsService.findById(orderItemNo);
    return ResponseEntity.ok(convertToResponse(orderDetails));
  }

  // 주문 상세 수정
  @PutMapping("/{orderItemNo}")
  public ResponseEntity<OrderDetailsDto.Response> updateOrderDetails(
      @PathVariable Long orderItemNo,
      @RequestBody OrderDetailsDto.UpdateRequest request) {

    OrderDetails orderDetails = orderDetailsService.findById(orderItemNo);
    orderDetails.setPurchaseQuantity(request.getPurchaseQuantity());
    orderDetails.setPurchasePrice(request.getPurchasePrice());
    orderDetails.setPurchaseAmount(request.getPurchasePrice() * request.getPurchaseQuantity());

    OrderDetails updatedOrderDetails = orderDetailsService.updateOrderDetails(orderDetails);

    return ResponseEntity.ok(convertToResponse(updatedOrderDetails));
  }

  // 주문 상세 삭제
  @DeleteMapping("/{orderItemNo}")
  public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long orderItemNo) {
    orderDetailsService.deleteOrderDetails(orderItemNo);
    return ResponseEntity.ok().build();
  }

  // 주문의 총 금액 조회
  @GetMapping("/order/{orderNo}/total")
  public ResponseEntity<Long> getOrderTotal(@PathVariable Long orderNo) {
    Order order = orderService.findById(orderNo);
    Long totalAmount = orderDetailsService.calculateTotalAmount(order);
    return ResponseEntity.ok(totalAmount);
  }

  // Entity -> Response DTO 변환
  private OrderDetailsDto.Response convertToResponse(OrderDetails orderDetails) {
    OrderDetailsDto.Response response = new OrderDetailsDto.Response();
    response.setOrderItemNo(orderDetails.getOrderItemNo());
    response.setBookNo(orderDetails.getBookNo());
    response.setPurchaseQuantity(orderDetails.getPurchaseQuantity());
    response.setPurchasePrice(orderDetails.getPurchasePrice());
    response.setPurchaseAmount(orderDetails.getPurchaseAmount());
    response.setOrderNo(orderDetails.getOrder().getOrderNo());
    return response;
  }
}