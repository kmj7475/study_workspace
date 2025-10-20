package com.example.demo.dto;

import com.example.demo.domain.Book;
import com.example.demo.domain.OrderDetails;

import lombok.Data;

@Data
public class OrderDetailsDto {
    private Long orderItemNo;
    private Integer bookNo;
    private Long purchaseQuantity;
    private Long purchasePrice;
    private Long purchaseAmount;
    private Long orderNo;

    // 요청용 DTO
    @Data
    public static class CreateRequest {
        private Integer bookNo;
        private Long purchaseQuantity;
        private Long purchasePrice;
        private Long purchaseAmount;
        private Long orderNo;

        /** ✅ DTO → Entity 변환 메서드 */
        public OrderDetails toEntity(Book book) {
            OrderDetails entity = new OrderDetails();
            entity.setBook(book);
            //entity.setOrder(order);
            entity.setPurchaseQuantity(this.purchaseQuantity);
            entity.setPurchasePrice(this.purchasePrice);
            entity.setPurchaseAmount(this.purchaseQuantity * this.purchasePrice);
            return entity;
        }
    }

    // 수정용 DTO
    @Data
    public static class UpdateRequest {
        private Long purchaseQuantity;
        private Long purchasePrice;
        private Long purchaseAmount;

        /** ✅ DTO → Entity 변환 메서드 */
        public void updateEntity(OrderDetails entity) {
            if (purchaseQuantity != null) entity.setPurchaseQuantity(purchaseQuantity);
            if (purchasePrice != null) entity.setPurchasePrice(purchasePrice);
            if (purchaseAmount != null) entity.setPurchaseAmount(purchaseAmount);
        }
    }

    // 응답용 DTO
    @Data
    public static class Response {
        private Long orderItemNo;
        private Integer bookNo;
        private Long purchaseQuantity;
        private Long purchasePrice;
        private Long purchaseAmount;
        private Long orderNo;

        /** ✅ Entity → DTO 변환 메서드 */
        public static Response fromEntity(OrderDetails entity) {
            Response dto = new Response();
            dto.setOrderItemNo(entity.getOrderItemNo());
            dto.setBookNo(entity.getBook().getBookNo());
            dto.setPurchaseQuantity(entity.getPurchaseQuantity());
            dto.setPurchasePrice(entity.getPurchasePrice());
            dto.setPurchaseAmount(entity.getPurchaseAmount());
            dto.setOrderNo(entity.getOrder().getOrderNo());
            return dto;
        }
    }
}