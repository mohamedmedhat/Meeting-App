package com.example.demo.mapper;

import com.example.demo.model.Image;
import com.example.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ProductMapper {

    public Product toEntity(String name, BigDecimal price, Image image) {
        Product product = new Product();
        product.setImage(image);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public Product updateToEntity(Product product, String name, BigDecimal price, Image image) {
        product.setImage(image);
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
