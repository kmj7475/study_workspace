package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.dto.OrderDetailViewDto;
import com.example.demo.service.BookService;
import com.example.demo.service.OrderDetailsService;
import com.example.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/order-details")
@Controller
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
    Book book = bookService.getBookByBookNo(request.getBookNo().intValue()).get();

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

  // 특정 회원(memberNo)의 주문 상세 리스트 페이징 조회 (API)
  @GetMapping("/member/{memberNo}")
  public ModelAndView getOrderDetailsByMemberPaged(
      @PathVariable Long memberNo, Pageable pageable) {
    List<OrderDetails> list = orderDetailsService.findByMemberNo(memberNo);
    //Page<OrderDetailsDto.Response> responsePage = page.map(this::convertToResponse);
    ModelAndView modelAndView = new ModelAndView("order/list");
    modelAndView.addObject("list", list);
    return modelAndView;
  }

  // 특정 회원의 주문 상세 리스트 페이징 조회 (뷰 템플릿)
  @GetMapping("/member/{memberNo}/list")
  public ModelAndView getOrderDetailsByMemberList(
      @PathVariable Long memberNo, 
      Pageable pageable) {
    Page<OrderDetailViewDto> page = orderDetailsService.findOrderDetailViewByMemberNo(memberNo, pageable);
    ModelAndView modelAndView = new ModelAndView("order/list");
    modelAndView.addObject("orderDetailPage", page);
    modelAndView.addObject("memberNo", memberNo);
    return modelAndView;
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