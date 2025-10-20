package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_no")
  private Long orderNo; //주문번호

  @Column(length = 20, nullable = false)
  private Date orderDate; //주문일자
  
  @Column(length = 10, nullable = false)
  private String orderStatus; //주문상태
  
  @Column(length = 10, nullable = false)
  private long orderTotal;  //합계
  
  @Column(length = 10, nullable = false)
  private long usedPoint;   //포인트 결제
  
  @Column(length = 10, nullable = false)
  private long paymentTotal;  //주문금액 ( 합계 - 포인트)
  
  @Column(length = 100, nullable = false)
  private String receiverName;  //받는사람 이름

  @Column(length = 100, nullable = false, unique = true)
  private String receiverAddr; //받는사람 주소

  @Column(length = 20, nullable = false)
  private String phone;   //받는사람 전화번호
    

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_no", nullable = false)
  private Member member;  // 주문회원

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) 
  private List<OrderDetails> orderDetailsList = new ArrayList<>(); //주문상세 목록

  public void addDetail(OrderDetails detail) {
    orderDetailsList.add(detail);
    detail.setOrder(this);
  }
}
