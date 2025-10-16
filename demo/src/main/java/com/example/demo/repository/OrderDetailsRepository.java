package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.domain.Member;
import com.example.demo.dto.OrderDetailViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
  // 주문에 해당하는 모든 주문 상세 조회
  List<OrderDetails> findByOrder(Order order);
  
  // 특정 멤버의 모든 주문 상세 조회
  List<OrderDetails> findByOrder_Member(Member member);

  // 특정 멤버의 주문 상세 내역 페이징 조회
  Page<OrderDetails> findByOrder_Member(Member member, Pageable pageable);

  // 특정 멤버의 주문 상세 내역 + 도서명 + 배송지 정보 포함 페이징 조회
  @Query(value = """
         SELECT new com.example.demo.dto.OrderDetailViewDto(
         od.orderItemNo, b.bookName, o.orderDate, od.purchaseQuantity, 
         od.purchaseAmount, od.bookNo, o.orderNo, o.receiverName, o.receiverAddr, o.phone) 
         FROM com.example.demo.domain.OrderDetails od 
         JOIN od.order o 
         JOIN com.example.demo.domain.Book b ON od.bookNo = b.bookNo 
         WHERE o.member = :member
         """) 
  Page<OrderDetailViewDto> findOrderDetailViewByMember(@Param("member") Member member, Pageable pageable);
} 
