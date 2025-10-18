package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity(name = "order_details")
public class OrderDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_item_no", length = 6, nullable = false)
  private Long orderItemNo; // 주문상세번호

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_no" , nullable = false)
  private Book book; // 책고유번호

  @Column(name = "purchase_quantity", precision = 10, scale = 0)
  private Long purchaseQuantity; // 구매수량

  @Column(name = "purchase_price", precision = 10, scale = 0)
  private Long purchasePrice; // 구매가격

  @Column(name = "purchase_amount")
  private Long purchaseAmount; // 구매금액

  @ManyToOne
  @JoinColumn(name = "order_no", nullable = false)
  private Order order;
}
