package com.example.demo.repository;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
  // 주문에 해당하는 모든 주문 상세 조회
  List<OrderDetails> findByOrder(Order order);
}
