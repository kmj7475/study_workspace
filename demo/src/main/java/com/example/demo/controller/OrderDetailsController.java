package com.example.demo.controller;


import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.service.OrderDetailsService;


import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    // // 주문 상세 단건 조회
    // @GetMapping("/{orderItemNo}")
    // public ResponseEntity<OrderDetailsDto.Response> getOrderDetail(@PathVariable Long orderItemNo) {
    //     return orderDetailsService.findById(orderItemNo)
    //             .map(OrderDetailsDto.Response::fromEntity)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // // 주문 상세 등록
    // @PostMapping
    // public ResponseEntity<OrderDetailsDto.Response> createOrderDetail(@RequestBody OrderDetailsDto.CreateRequest dto) {
    //     OrderDetails saved = orderDetailsService.createOrderDetails(dto);
    //     return ResponseEntity.ok(OrderDetailsDto.Response.fromEntity(saved));
    // }

    // // 주문 상세 수정
    // @PutMapping("/{orderItemNo}")
    // public ResponseEntity<OrderDetailsDto.Response> updateOrderDetail(
    //         @PathVariable Long orderItemNo,
    //         @RequestBody OrderDetailsDto.UpdateRequest dto
    // ) {
    //     OrderDetails updated = orderDetailsService.updateOrderDetails(orderItemNo, dto);
    //     return ResponseEntity.ok(OrderDetailsDto.Response.fromEntity(updated));
    // }

    // // 주문 상세 삭제
    // @DeleteMapping("/{orderItemNo}")
    // public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long orderItemNo) {
    //     orderDetailsService.deleteOrderDetails(orderItemNo);
    //     return ResponseEntity.noContent().build();
    // }
}