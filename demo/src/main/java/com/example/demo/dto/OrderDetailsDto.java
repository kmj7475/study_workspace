package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {
  private Long orderItemNo;
  private Long bookNo;
  private Long purchaseQuantity;
  private Long purchasePrice;
  private Long purchaseAmount;
  private Long orderNo;

  // 요청용 DTO
  @Data
  public static class CreateRequest {
    private Long bookNo;
    private Long purchaseQuantity;
    private Long orderNo;
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
    private Long bookNo;
    private Long purchaseQuantity;
    private Long purchasePrice;
    private Long purchaseAmount;
    private Long orderNo;
  }
}