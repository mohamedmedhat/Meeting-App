package com.example.demo.service.cart;

import com.example.demo.model.Cart;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    Cart createCart();
    ResponseEntity<Void> deleteCart(String id);
    Cart updateCart(String id);

}
