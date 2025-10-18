package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.domain.Book;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.repository.OrderDetailsRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderDetailsService;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    // 단건 조회
    @Override
    public Optional<OrderDetails> findById(Long orderItemNo) {
        return orderDetailsRepository.findById(orderItemNo);
    }

    // 주문번호 기준 상세 조회
    @Override
    public List<OrderDetailsDto.Response> findByOrderNo(Long orderNo) {
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return order.getOrderDetailsList()
                .stream()
                .map(OrderDetailsDto.Response::fromEntity)
                .collect(Collectors.toList());
    }

    // 등록
    @Override
    @Transactional
    public OrderDetails createOrderDetail(OrderDetailsDto.CreateRequest dto) {
        Book book = bookRepository.findById(dto.getBookNo())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Order order = orderRepository.findById(dto.getOrderNo())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDetails entity = new OrderDetails();
        entity.setOrder(order);
        entity.setBook(book);
        entity.setPurchaseQuantity(dto.getPurchaseQuantity());
        entity.setPurchasePrice(book.getPrice());
        entity.setPurchaseAmount(book.getPrice() * dto.getPurchaseQuantity());

        return orderDetailsRepository.save(entity);
    }

    // 수정
    @Override
    @Transactional
    public OrderDetails updateOrderDetail(Long orderItemNo, OrderDetailsDto.UpdateRequest dto) {
        OrderDetails entity = orderDetailsRepository.findById(orderItemNo)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        entity.setPurchaseQuantity(dto.getPurchaseQuantity());
        entity.setPurchasePrice(dto.getPurchasePrice());
        entity.setPurchaseAmount(dto.getPurchasePrice() * dto.getPurchaseQuantity());

        return orderDetailsRepository.save(entity);
    }

    // 삭제
    @Override
    @Transactional
    public void deleteOrderDetail(Long orderItemNo) {
        OrderDetails entity = orderDetailsRepository.findById(orderItemNo)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        orderDetailsRepository.delete(entity);
    }
}