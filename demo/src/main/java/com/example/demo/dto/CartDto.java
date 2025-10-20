package com.example.demo.dto;

import com.example.demo.domain.Book;
import com.example.demo.domain.Cart;
import com.example.demo.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CartDto {

    private Long cartNo;
    private Long memberNo;
    private Integer bookNo;
    private String bookName;
    private Long price;
    private Long quantity;
    private Long totalPrice;

    /* ===========================
       ✅ 요청 DTO (Create)
       =========================== */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        private Long memberNo;
        private Integer bookNo;
        private Long quantity;

        // DTO → Entity 변환
        public Cart toEntity(Member member, Book book) {
            Cart cart = new Cart();
            cart.setMember(member);
            cart.setBook(book);
            cart.setQuantity(quantity);
            return cart;
        }
    }

    /* ===========================
       ✅ 요청 DTO (Update)
       =========================== */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {
        private Long cartNo;
        private Long quantity;
    }

    /* ===========================
       ✅ 응답 DTO (Response)
       =========================== */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long cartNo;
        private Long memberNo;
        private Integer bookNo;
        private String bookName;
        private Long price;
        private Long quantity;
        private Long totalPrice;

        // Entity → DTO 변환
        public static Response fromEntity(Cart cart) {
            return Response.builder()
                    .cartNo(cart.getCartNo())
                    .memberNo(cart.getMember().getMemberNo())
                    .bookNo(cart.getBook().getBookNo())
                    .bookName(cart.getBook().getBookName())
                    .price(cart.getBook().getPrice())
                    .quantity(cart.getQuantity())
                    .totalPrice(cart.getBook().getPrice() * cart.getQuantity())
                    .build();
        }
    }
}