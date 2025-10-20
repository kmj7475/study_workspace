package com.example.demo.service;

import java.util.List;
import com.example.demo.domain.Cart;

public interface CartService {

    /**
     * 현재 로그인한 사용자의 장바구니 엔티티 리스트
     */
    public List<Cart> getCartItemsForCurrentUser(Long memberNo) ;

    /**
     * 장바구니에 추가
     */
    public Cart addToCart(Cart cart) ;

    /**
     * 장바구니 아이템 삭제
     */
    public void removeFromCart(Long cartNo);

    /**
     * 장바구니 수량 수정
     */
    public Cart updateQuantity(Long cartNo, Long quantity) ;
}

