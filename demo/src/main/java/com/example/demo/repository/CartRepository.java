package com.example.demo.repository;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByMember(Member member);
}