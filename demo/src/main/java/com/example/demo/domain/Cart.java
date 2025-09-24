package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_no", nullable = false)
  private Long cartNo; // 장바구니번호

  @Column(name = "member_no", nullable = false)
  private Long memberNo; // 회원번호

  @Column(name = "book_no", nullable = false)
  private Long bookNo; // 책고유번호

  @Column(name = "quantity", nullable = false)
  private Long quantity; // 선택수량
}
