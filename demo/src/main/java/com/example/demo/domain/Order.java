package com.example.demo.domain;

import java.sql.Date;
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
  private Long orderNo;

  @Column(length = 100, nullable = false)
  private String receiverName;
  @Column(length = 100, nullable = false, unique = true)
  private String receiverAddr;
  @Column(length = 20, nullable = false)
  private Date orderDate;
  @Column(length = 10, nullable = false)
  private String orderStatus;
  @Column(length = 10, nullable = false)
  private long orderTotal;
  @Column(length = 10, nullable = false)
  private long usedPoint;
  @Column(length = 10, nullable = false)
  private long paymentTotal;
  @Column(length = 20, nullable = false)
  private String phone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  @JoinColumn(name = "member_no", nullable = false)
  private Member member;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderDetails> orderDetailsList = new ArrayList<>();

  public void addDetail(OrderDetails detail) {
    orderDetailsList.add(detail);
    detail.setOrder(this);
  }
}
