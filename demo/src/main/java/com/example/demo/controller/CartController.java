package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Book;
import com.example.demo.domain.Cart;
import com.example.demo.domain.Member;
import com.example.demo.dto.CartDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
  private final CartService cartService;
  private final MemberService memberService;
  private final BookService bookService;
  
  private final BookRepository bookRepository;


  /** 장바구니 페이지 조회 */
  @GetMapping
  public String cartPage(Model model) {
      // 현재 로그인 회원 (테스트용: memberNo=1)
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

      return "orders/cart";
  }

  /** 장바구니에 추가 */
  @PostMapping("/add")
  public String addToCart(@RequestParam Integer bookNo,
                          @RequestParam Long quantity) {

      Member member = memberService.findById(1L);
      Book book = bookRepository.findByBookNo(bookNo).orElseThrow(() -> new RuntimeException("책 없음"));

      Cart cart = new Cart();
      cart.setMember(member);
      cart.setBook(book);
      cart.setQuantity(quantity);

      cartService.addToCart(cart);

      return "redirect:/cart";
  }

  /** 수량 변경 */
  @PostMapping("/update")
  public String updateQuantity(@RequestParam Long cartNo,
                               @RequestParam Long quantity) {
      cartService.updateQuantity(cartNo, quantity);
      return "redirect:/cart";
  }

  /** 장바구니 아이템 삭제 */
  @PostMapping("/delete")
  public String deleteCartItem(@RequestParam Long cartNo) {
      cartService.removeFromCart(cartNo);
      return "redirect:/cart";
  }
}