package com.example.demo.service.cart;

import com.example.demo.model.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Override
    public Cart createCart() {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteCart(String id) {
        return null;
    }

    @Override
    public Cart updateCart(String id) {
        return null;
    }
}
