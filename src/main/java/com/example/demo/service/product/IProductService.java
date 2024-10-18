package com.example.demo.service.product;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

public interface IProductService {
    Boolean createProduct(String name, BigDecimal price, MultipartFile file) throws IOException;

    Product updateProduct(String id, String name, BigDecimal price, MultipartFile file) throws IOException;

    ResponseEntity<Void> deleteProduct(String id);

    Product findProductById(String id);

    Page<Product> findAll(int page, int size);

    Page<Product> findProductsByHighestPrice(int page, int size);

    Page<Product> findProductsByLowestPrice(int page, int size);

    Page<Product> findProductsInRange(BigDecimal start, BigDecimal end, int page, int size);

    Page<Product> findProductsByName(String name, int page, int size);
}
