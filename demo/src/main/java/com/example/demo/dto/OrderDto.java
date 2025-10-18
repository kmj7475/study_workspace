package com.example.demo.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.Order;

import lombok.Data;


@Data
public class OrderDto {

    private Long orderNo;
    private String receiverName;
    private String receiverAddr;
    private Date orderDate;
    private String orderStatus;
    private Long orderTotal;
    private Long usedPoint;
    private Long paymentTotal;
    private String phone;

    private List<OrderDetailsDto.Response> orderDetailsList;

    // =================================================
    // 등록 요청용 DTO
    // =================================================
    @Data
    public static class CreateRequest {
        private String receiverName;
        private String receiverAddr;
        private String orderStatus;
        private Long orderTotal;
        private Long usedPoint;
        private Long paymentTotal;
        private String phone;
        private Long memberNo;

        // 여기 추가
        private List<OrderDetailsDto.CreateRequest> orderDetailsList;
    }

    // =================================================
    // 수정 요청용 DTO
    // =================================================
    @Data
    public static class UpdateRequest {
        private String receiverName;
        private String receiverAddr;
        private String orderStatus;
        private Long orderTotal;
        private Long usedPoint;
        private Long paymentTotal;
        private String phone;

        // 여기 추가
        private List<OrderDetailsDto.CreateRequest> orderDetailsList;
    }

    // =================================================
    // 응답용 DTO
    // =================================================
    @Data
    public static class Response {
        private Long orderNo;
        private String receiverName;
        private String receiverAddr;
        private Date orderDate;
        private String orderStatus;
        private Long orderTotal;
        private Long usedPoint;
        private Long paymentTotal;
        private String phone;
        private List<OrderDetailsDto.Response> orderDetailsList;

        public static Response fromEntity(Order order) {
            Response dto = new Response();
            dto.setOrderNo(order.getOrderNo());
            dto.setReceiverName(order.getReceiverName());
            dto.setReceiverAddr(order.getReceiverAddr());
            dto.setOrderDate(order.getOrderDate());
            dto.setOrderStatus(order.getOrderStatus());
            dto.setOrderTotal(order.getOrderTotal());
            dto.setUsedPoint(order.getUsedPoint());
            dto.setPaymentTotal(order.getPaymentTotal());
            dto.setPhone(order.getPhone());
            dto.setOrderDetailsList(
                    order.getOrderDetailsList().stream()
                            .map(OrderDetailsDto.Response::fromEntity)
                            .collect(Collectors.toList())
            );
            return dto;
        }
    }
}