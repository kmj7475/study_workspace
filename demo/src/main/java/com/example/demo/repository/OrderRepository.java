package com.example.demo.repository;

import com.example.demo.domain.Order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  
  List<Order> findByMember_MemberNo(Long memberNo);

  Page<Order> findByMember_MemberNo(Long memberNo, Pageable pageable);

  Page<Order> findByOrderDateBetween(LocalDate start, LocalDate end, Pageable pageable);

  Page<Order> findByMember_MemberNoAndOrderDateBetween(Long memberNo, LocalDate start, LocalDate end, Pageable pageable);
}
