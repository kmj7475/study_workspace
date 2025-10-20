package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetails;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.MemberService;
import com.example.demo.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    //private final BookService bookService;
    private final BookRepository bookRepository;

    private final CartService cartService;



    // ✅ [1] 주문 등록 폼
    @GetMapping
    public String showCreateForm(Model model) {
        model.addAttribute("order", new OrderDto.CreateRequest());
        Long memberNo = 1151l;
        List<Cart> cartEntities = cartService.getCartItemsForCurrentUser(memberNo);
        List<CartDto.Response> cartItems = cartEntities.stream()
                .map(CartDto.Response::fromEntity)
                .collect(Collectors.toList());
  
        long totalAmount = cartItems.stream()
                .mapToLong(item -> item.getPrice() * item.getQuantity())
                .sum();
  
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "orders/form";
    }

    // ✅ [2] 주문 등록 처리
    @PostMapping
    @ResponseBody
    public String createOrder(@RequestBody OrderDto.CreateRequest dto) {
        System.out.println(dto);
        Long memberNo = 1151l;
        // 서비스는 엔티티를 기대하므로, 컨트롤러에서 member 조회 + orderDetails 변환 후 toEntity(member, details) 호출
        
        Member member = memberService.findById(memberNo); // 서비스/리포지토리에서 조회
        List<OrderDetails> details = dto.getOrderDetailsList() != null
               ? dto.getOrderDetailsList().stream()
                    .map(d -> d.toEntity(bookRepository.findById(d.getBookNo()).orElseThrow()))
                    .collect(Collectors.toList())
               : null;
        Order order = dto.toEntity(member, details);
        orderService.createOrder(order);
        return "ok";
    }

    // ✅ [3] 주문 수정 폼
    @GetMapping("/{orderNo}/edit")
    public String showEditForm(@PathVariable Long orderNo, Model model) {
        Optional<Order> orderOpt = orderService.findById(orderNo);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", OrderDto.Response.fromEntity(orderOpt.get()));
            return "orders/edit";
        } else {
            return "redirect:/orders/member";
        }
    }

    // ✅ [4] 주문 수정 처리
    @PostMapping("/{orderNo}/edit")
    public String updateOrder(@PathVariable Long orderNo, @ModelAttribute OrderDto.UpdateRequest dto) {
        Order updated = dto.toEntity();
        orderService.updateOrder(orderNo, updated);
        return "redirect:/orders/member";
    }

    // ✅ [5] 주문 삭제
    @PostMapping("/{orderNo}/delete")
    public String deleteOrder(@PathVariable Long orderNo) {
        orderService.deleteOrder(orderNo);
        return "redirect:/orders/member";
    }

    // ✅ [6] 단건 조회 (상세보기)
    @GetMapping("/{orderNo}")
    public String getOrder(@PathVariable Long orderNo, Model model) {
        Optional<Order> orderOpt = orderService.findById(orderNo);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", OrderDto.Response.fromEntity(orderOpt.get()));
            return "orders/detail";
        } else {
            return "redirect:/orders";
        }
    }

    // ✅ [7] 회원별 주문내역 조회
    @GetMapping("/member")
    public String getOrdersByMember(
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
    @RequestParam(required = false) String status,
    Model model) {
        //session에서 가져오기
        Long memberNo = 1151L; 
        List<OrderDto.Response> orders = orderService.findByMemberNo(memberNo)
                .stream()
                .map(OrderDto.Response::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("orders", orders);
        model.addAttribute("memberNo", memberNo);
        return "orders/member-orders";
    }

    // ✅ [8] 관리자용 전체 주문 조회 (회원번호 + 날짜 + 페이징)
    @GetMapping("/search")
    public String searchOrders(
            @RequestParam(required = false) Long memberNo,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PageableDefault(size = 10, sort = "orderDate") Pageable pageable,
            Model model
    ) {
        Page<com.example.demo.dto.OrderDto.Response> orders = orderService.searchOrders(memberNo, startDate, endDate, pageable)
                .map(OrderDto.Response::fromEntity);

        model.addAttribute("orders", orders.getContent());
        model.addAttribute("page", orders);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "orders/list";
    }
}
