package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 회원별 주문 조회 (페이징)
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<Page<OrderDto.Response>> getOrdersByMember(
            @PathVariable Long memberNo,
            Pageable pageable
    ) {
        Page<OrderDto.Response> orders = orderService.findOrdersByMember(memberNo, pageable);
        return ResponseEntity.ok(orders);
    }

    // 관리자 전체 주문 조회 (회원번호/날짜 범위 필터)
    // @GetMapping("/admin")
    // public ResponseEntity<Page<OrderDto.Response>> getOrdersForAdmin(
    //         @RequestParam(required = false) Long memberNo,
    //         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    //         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
    //         Pageable pageable
    // ) {
    //     Page<OrderDto.Response> orders = orderService.findOrdersForAdmin(memberNo, startDate, endDate, pageable);
    //     return ResponseEntity.ok(orders);
    // }

    // 주문 등록 (Order + OrderDetails 포함)
    @PostMapping
    public ResponseEntity<OrderDto.Response> createOrder(@RequestBody OrderDto.CreateRequest dto) {
        return ResponseEntity.ok(OrderDto.Response.fromEntity(orderService.createOrder(dto)));
    }

    // 주문 수정 (Order + OrderDetails 포함)
    @PutMapping("/{orderNo}")
    public ResponseEntity<OrderDto.Response> updateOrder(
            @PathVariable Long orderNo,
            @RequestBody OrderDto.UpdateRequest dto
    ) {
        return ResponseEntity.ok(OrderDto.Response.fromEntity(orderService.updateOrder(orderNo, dto)));
    }

    // 주문 삭제
    @DeleteMapping("/{orderNo}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderNo) {
        orderService.deleteOrder(orderNo);
        return ResponseEntity.noContent().build();
    }

    // 단건 조회
    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderDto.Response> getOrder(@PathVariable Long orderNo) {
        return orderService.findById(orderNo)
                .map(OrderDto.Response::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}