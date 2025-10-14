package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderDetailViewDto {
    private Long orderItemNo;
    private String bookName;        // 도서명
    private Date orderDate;    // 주문일자
    private Long purchaseQuantity;  // 구매수량
    private Long purchaseAmount;    // 구매금액
    private Long bookNo;            // 도서번호
    private Long orderNo;           // 주문번호
    private String receiverName;     // 수령인명
    private String receiverAddr;    // 배송지주소
    private String phone;           // 연락처

    // JPQL용 생성자 (순서 중요!)
    public OrderDetailViewDto(Long orderItemNo, String bookName, Date orderDate, 
                             Long purchaseQuantity, Long purchaseAmount, Long bookNo, Long orderNo,
                             String receiverName, String receiverAddr, String phone) {
        this.orderItemNo = orderItemNo;
        this.bookName = bookName; 
        this.orderDate = orderDate;
        this.purchaseQuantity = purchaseQuantity;
        this.purchaseAmount = purchaseAmount;
        this.bookNo = bookNo; 
        this.orderNo = orderNo;
        this.receiverName = receiverName;
        this.receiverAddr = receiverAddr;
        this.phone = phone;
    } 
}
