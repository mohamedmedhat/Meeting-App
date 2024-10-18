package com.example.demo.controller;

import com.example.demo.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/carts/")
public class CartController {
    private final ICartService cartService;
}
