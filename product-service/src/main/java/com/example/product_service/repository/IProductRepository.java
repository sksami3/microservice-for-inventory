package com.example.product_service.repository;

import com.example.product_service.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.isDelete = true WHERE p.Id = :productId")
    void softDeleteProduct(long productId);
    List<Product> findByIsDeleteFalse();
}
