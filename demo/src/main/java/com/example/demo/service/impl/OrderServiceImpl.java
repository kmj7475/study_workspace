package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 단건 조회
    @Override
    public Optional<Order> findById(Long orderNo) {
        return orderRepository.findById(orderNo);
    }

    // 회원별 주문 조회 (페이징)
    @Override
    public Page<OrderDto.Response> findOrdersByMember(Long memberNo, Pageable pageable) {
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Page<Order> orders = orderRepository.findByMember(member, pageable);
        return orders.map(OrderDto.Response::fromEntity);
    }

    // 등록 (Order + OrderDetails)
    @Override
    @Transactional
    public Order createOrder(OrderDto.CreateRequest dto) {
        Member member = memberRepository.findById(dto.getMemberNo())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Order order = new Order();
        order.setMember(member);
        order.setReceiverName(dto.getReceiverName());
        order.setReceiverAddr(dto.getReceiverAddr());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrderTotal(dto.getOrderTotal());
        order.setUsedPoint(dto.getUsedPoint());
        order.setPaymentTotal(dto.getPaymentTotal());
        order.setPhone(dto.getPhone());

        // 상세 추가
        if (dto.getOrderDetailsList() != null) {
            for (OrderDetailsDto.CreateRequest detailDto : dto.getOrderDetailsList()) {
                Book book = bookRepository.findById(detailDto.getBookNo())
                        .orElseThrow(() -> new RuntimeException("Book not found"));
                OrderDetails detail = new OrderDetails();
                detail.setOrder(order);
                detail.setBook(book);
                detail.setPurchaseQuantity(detailDto.getPurchaseQuantity());
                detail.setPurchasePrice(book.getPrice());
                detail.setPurchaseAmount(book.getPrice() * detailDto.getPurchaseQuantity());
                order.getOrderDetailsList().add(detail);
            }
        }

        return orderRepository.save(order);
    }

    // 수정 (Order + OrderDetails)
    @Override
    @Transactional
    public Order updateOrder(Long orderNo, OrderDto.UpdateRequest dto) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setReceiverName(dto.getReceiverName());
        order.setReceiverAddr(dto.getReceiverAddr());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrderTotal(dto.getOrderTotal());
        order.setUsedPoint(dto.getUsedPoint());
        order.setPaymentTotal(dto.getPaymentTotal());
        order.setPhone(dto.getPhone());

        // 상세 업데이트: 기존 상세 삭제 후 새로 등록
        if (dto.getOrderDetailsList() != null) {
            order.getOrderDetailsList().clear();
            for (OrderDetailsDto.CreateRequest detailDto : dto.getOrderDetailsList()) {
                Book book = bookRepository.findById(detailDto.getBookNo())
                        .orElseThrow(() -> new RuntimeException("Book not found"));
                OrderDetails detail = new OrderDetails();
                detail.setOrder(order);
                detail.setBook(book);
                detail.setPurchaseQuantity(detailDto.getPurchaseQuantity());
                detail.setPurchasePrice(book.getPrice());
                detail.setPurchaseAmount(book.getPrice() * detailDto.getPurchaseQuantity());
                order.getOrderDetailsList().add(detail);
            }
        }

        return orderRepository.save(order);
    }

    // 삭제 (Order + OrderDetails)
    @Override
    @Transactional
    public void deleteOrder(Long orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepository.delete(order); // Cascade.ALL 덕분에 상세도 삭제됨
    }
}