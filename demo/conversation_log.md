# 개발 대화 기록

## 1. Order와 OrderDetails의 관계
- Order와 OrderDetails는 일대다(1:N) 관계로 매핑
- Order: `@OneToMany(mappedBy = "order", ...) private List<OrderDetails> orderDetailsList;`
- OrderDetails: `@ManyToOne @JoinColumn(name = "order_no") private Order order;`

## 2. OrderRepository, OrderDetailsRepository 생성
- OrderRepository, OrderDetailsRepository 각각 JPA Repository로 생성

## 3. 단위테스트 추가
- OrderDetailsRepositoryTest: Order와 OrderDetails를 함께 저장하는 테스트
- OrderTest: Order의 addDetail 메서드 단위테스트

## 4. Order 엔티티에 addDetail 메서드 추가
- addDetail(OrderDetails detail): 리스트에 추가 및 양방향 연관관계 설정

## 5. 주요 코드 변경 내역
```java
// Order.java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderDetails> orderDetailsList = new ArrayList<>();
public void addDetail(OrderDetails detail) {
    orderDetailsList.add(detail);
    detail.setOrder(this);
}

// OrderDetails.java
@ManyToOne
@JoinColumn(name = "order_no")
private Order order;

// OrderRepository.java
public interface OrderRepository extends JpaRepository<Order, Long> {}

// OrderDetailsRepository.java
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {}
```

## 6. 테스트 코드 예시
```java
// OrderTest.java
Order order = new Order();
OrderDetails details = new OrderDetails();
order.addDetail(details);
assertEquals(1, order.getOrderDetailsList().size());
assertSame(order, details.getOrder());
```

---

이 파일은 개발 히스토리 및 코드 변경 내역을 기록한 것입니다.
