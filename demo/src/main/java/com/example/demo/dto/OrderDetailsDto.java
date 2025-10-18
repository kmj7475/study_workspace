package com.example.demo.dto;

import com.example.demo.domain.OrderDetails;

import lombok.Data;

@Data
public class OrderDetailsDto {
  private Long orderItemNo;
  private Long bookNo;
  private Long purchaseQuantity;
  private Long purchasePrice;
  private Long purchaseAmount;
  private Long orderNo;

  // =================================================
  // 하위 static DTO 클래스
  // =================================================

  // 요청용 DTO
  @Data
  public static class CreateRequest {
    private Long orderNo;       // 주문번호
    private Integer bookNo;     // 책번호
    private Long purchaseQuantity; // 구매수량
  }

  // 수정용 DTO
  @Data
  public static class UpdateRequest {
    private Long purchaseQuantity;
    private Long purchasePrice;
  }

  // 응답용 DTO
  @Data
  public static class Response {
    private Long orderItemNo;
    private Integer bookNo;
    private String bookName;
    private Long purchaseQuantity;
    private Long purchasePrice;
    private Long purchaseAmount;
    private Long orderNo;
    
    public static Response fromEntity(OrderDetails entity) {
      Response dto = new Response();
      dto.setOrderItemNo(entity.getOrderItemNo());
      dto.setOrderNo(entity.getOrder().getOrderNo());
      dto.setBookNo(entity.getBook().getBookNo());
      dto.setBookName(entity.getBook().getBookName());
      dto.setPurchaseQuantity(entity.getPurchaseQuantity());
      dto.setPurchasePrice(entity.getPurchasePrice());
      dto.setPurchaseAmount(entity.getPurchaseAmount());
      return dto;
  }
  }
}