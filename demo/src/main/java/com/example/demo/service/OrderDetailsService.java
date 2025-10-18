package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.OrderDetailsDto;

public interface OrderDetailsService {

   // 단건 조회
   Optional<OrderDetails> findById(Long orderItemNo);

   // 주문별 주문 상세 조회
   List<OrderDetailsDto.Response> findByOrderNo(Long orderNo);

   // 등록
   OrderDetails createOrderDetail(OrderDetailsDto.CreateRequest dto);

   // 수정
   OrderDetails updateOrderDetail(Long orderItemNo, OrderDetailsDto.UpdateRequest dto);

   // 삭제
   void deleteOrderDetail(Long orderItemNo);

}