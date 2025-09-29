package com.example.demo.dto;

import lombok.Data;

public class OrderDto {

  @Data
  public static class CreateRequest {
    private Long memberNo;
    private String receiverName;
    private String receiverAddr;
    private String phone;
  }

  @Data
  public static class Response {
    private Long orderNo;
    private Long memberNo;
    private String receiverName;
    private String receiverAddr;
    private String phone;
    private Long totalAmount;
    private String orderStatus;
  }
}