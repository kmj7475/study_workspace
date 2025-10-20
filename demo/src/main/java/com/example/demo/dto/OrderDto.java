package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;

@Data
public class OrderDto {

    private Long orderNo;
    private Long memberNo;
    private String receiverName;
    private String receiverAddr;
    private Date orderDate;
    private String orderStatus;
    private Long orderTotal;
    private Long usedPoint;
    private Long paymentTotal;
    private String phone;

    private List<OrderDetailsDto.CreateRequest> orderDetailsList;

    // ========================
    // CreateRequest
    // ========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private Long memberNo;
        private String receiverName;
        private String receiverAddr;
        private String orderStatus;
        private Long orderTotal;
        private Long usedPoint;
        private Long paymentTotal;
        private String phone;
        private List<OrderDetailsDto.CreateRequest> orderDetailsList;

        /**
         * 기본적으로 Order 엔티티의 primitive 필드만 세팅한 엔티티 반환.
         * - member, orderDetailsList 는 null로 남김(서비스에서 채울 것)
         */
        public Order toEntity() {
            Order order = new Order();
            order.setReceiverName(this.receiverName);
            order.setReceiverAddr(this.receiverAddr);
            order.setOrderDate(new Date()); // 기본은 현재 시각(필요시 서비스에서 재설정)
            order.setOrderStatus("ORDER");
            order.setOrderTotal(this.orderTotal != null ? this.orderTotal : 0L);
            order.setUsedPoint(this.usedPoint != null ? this.usedPoint : 0L);
            order.setPaymentTotal(this.paymentTotal != null ? this.paymentTotal : 0L);
            order.setPhone(this.phone);
            return order;
        }

        /**
         * member, orderDetailsList 를 함께 제공해서 완전히 구성된 Order 반환.
         * 서비스에서 Member 조회, OrderDetails 변환 후 호출하는 용도로 사용.
         */
        public Order toEntity(Member member, List<OrderDetails> orderDetails) {
            Order order = this.toEntity();
            order.setMember(member);
            if (orderDetails != null) {
                orderDetails.forEach(d -> order.addDetail(d));
                
                // 편의: 각 detail에 부모 Order 세팅
                //order.setOrderDetailsList(orderDetails);
                //orderDetails.forEach(d -> d.setOrder(order));

            }
            return order;
        }
    }

    // ========================
    // UpdateRequest
    // ========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String receiverName;
        private String receiverAddr;
        private String orderStatus;
        private Long orderTotal;
        private Long usedPoint;
        private Long paymentTotal;
        private String phone;
        private List<OrderDetailsDto.CreateRequest> orderDetailsList;

        /**
         * 수정용으로 Order 엔티티의 변경 가능한 필드만 채운 엔티티 반환.
         * 실제 업데이트는 서비스에서 기존 엔티티를 찾아 해당 필드로 덮어씌움.
         */
        public Order toEntity() {
            Order order = new Order();
            order.setReceiverName(this.receiverName);
            order.setReceiverAddr(this.receiverAddr);
            order.setOrderStatus(this.orderStatus);
            order.setOrderTotal(this.orderTotal != null ? this.orderTotal : 0L);
            order.setUsedPoint(this.usedPoint != null ? this.usedPoint : 0L);
            order.setPaymentTotal(this.paymentTotal != null ? this.paymentTotal : 0L);
            order.setPhone(this.phone);
            return order;
        }
    }

    // ========================
    // Response
    // ========================
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long orderNo;
        private Long memberNo;
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
            Response r = new Response();
            r.setOrderNo(order.getOrderNo());
            if (order.getMember() != null) r.setMemberNo(order.getMember().getMemberNo());
            r.setReceiverName(order.getReceiverName());
            r.setReceiverAddr(order.getReceiverAddr());
            r.setOrderDate(order.getOrderDate());
            r.setOrderStatus(order.getOrderStatus());
            r.setOrderTotal(order.getOrderTotal());
            r.setUsedPoint(order.getUsedPoint());
            r.setPaymentTotal(order.getPaymentTotal());
            r.setPhone(order.getPhone());

            if (order.getOrderDetailsList() != null) {
                r.setOrderDetailsList(
                    order.getOrderDetailsList().stream()
                         .map(OrderDetailsDto.Response::fromEntity)
                         .collect(Collectors.toList())
                );
            }
            return r;
        }
    }
}
