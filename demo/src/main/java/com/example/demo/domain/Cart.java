package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_no", nullable = false)
  private Long cartNo; // 장바구니번호

  @ManyToOne
  @JoinColumn(name = "member_no", referencedColumnName = "member_no", nullable = false)
  private Member member; // 회원

  @ManyToOne
  @JoinColumn(name = "book_no", referencedColumnName = "book_no", nullable = false)
  private Book book; // 책

  @Column(name = "quantity", nullable = false)
  private Long quantity; // 선택수량
}
