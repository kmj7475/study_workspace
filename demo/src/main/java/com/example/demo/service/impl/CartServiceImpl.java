package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Member;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, MemberRepository memberRepository) {
        this.cartRepository = cartRepository;
        this.memberRepository = memberRepository;
    }


    /**
     * 현재 로그인한 사용자의 장바구니 엔티티 리스트
     */
    public List<Cart> getCartItemsForCurrentUser(Long memberNo) {
        // 테스트용: memberNo = 1L 하드코딩
        Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        return cartRepository.findByMember(member);
    }

    /**
     * 장바구니에 추가
     */
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * 장바구니 아이템 삭제
     */
    public void removeFromCart(Long cartNo) {
        cartRepository.deleteById(cartNo);
    }

    /**
     * 장바구니 수량 수정
     */
    public Cart updateQuantity(Long cartNo, Long quantity) {
        Cart cart = cartRepository.findById(cartNo)
                .orElseThrow(() -> new RuntimeException("장바구니 아이템이 존재하지 않습니다."));
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }
}

