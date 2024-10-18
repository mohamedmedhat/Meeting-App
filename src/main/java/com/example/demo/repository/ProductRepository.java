package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(sort = "{ 'price' : -1 }")
    Page<Product> findTopByOrderByPriceDesc(Pageable pageable);

    @Query(sort = "{ 'price' : 1 }")
    Page<Product> findTopByOrderByPriceAsc(Pageable pageable);

    Page<Product> findByPriceLessThanEqualAndPriceGreaterThanEqual(BigDecimal start, BigDecimal end, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);
}
