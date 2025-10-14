package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.domain.Member;
import com.example.demo.dto.OrderDetailViewDto;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderDetailsService {

  private final OrderDetailsRepository orderDetailsRepository;
  private final MemberRepository memberRepository;

  // 주문 상세 생성
  @Transactional
  public OrderDetails createOrderDetails(OrderDetails orderDetails) {
    return orderDetailsRepository.save(orderDetails);
  }

  // 주문번호로 주문 상세 목록 조회
  public List<OrderDetails> findByOrder(Order order) {
    return orderDetailsRepository.findByOrder(order);
  }

  // 주문 상세 번호로 조회
  public OrderDetails findById(Long orderItemNo) {
    return orderDetailsRepository.findById(orderItemNo)
        .orElseThrow(() -> new RuntimeException("주문 상세를 찾을 수 없습니다."));
  }

  // 주문 상세 수정
  @Transactional
  public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
    OrderDetails existingOrderDetails = findById(orderDetails.getOrderItemNo());
    existingOrderDetails.setPurchaseQuantity(orderDetails.getPurchaseQuantity());
    existingOrderDetails.setPurchasePrice(orderDetails.getPurchasePrice());
    existingOrderDetails.setPurchaseAmount(orderDetails.getPurchaseAmount());
    return orderDetailsRepository.save(existingOrderDetails);
  }

  // 주문 상세 삭제
  @Transactional
  public void deleteOrderDetails(Long orderItemNo) {
    orderDetailsRepository.deleteById(orderItemNo);
  }

  // 주문의 총 금액 계산
  public Long calculateTotalAmount(Order order) {
    List<OrderDetails> orderDetailsList = findByOrder(order);
    return orderDetailsList.stream()
        .mapToLong(OrderDetails::getPurchaseAmount)
        .sum();
  }

  // 특정 멤버의 주문 상세 리스트 조회
  public List<OrderDetails> findByMember(Member member) {
    return orderDetailsRepository.findByOrder_Member(member);
  }

  // memberNo로 상세 주문 내역 조회
  public List<OrderDetails> findByMemberNo(Long memberNo) {
    Member member = memberRepository.findById(memberNo)
      .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    return orderDetailsRepository.findByOrder_Member(member);
  }

  // memberNo와 페이징을 통한 상세 주문 내역 조회
  public Page<OrderDetails> findByMemberNo(Long memberNo, Pageable pageable) {
    Member member = memberRepository.findById(memberNo)
      .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    return orderDetailsRepository.findByOrder_Member(member, pageable);
  }

  // memberNo와 페이징을 통한 상세 주문 내역 조회 (도서명 포함)
  public Page<OrderDetailViewDto> findOrderDetailViewByMemberNo(Long memberNo, Pageable pageable) {
    Member member = memberRepository.findById(memberNo)
      .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    return orderDetailsRepository.findOrderDetailViewByMember(member, pageable);
  }
}