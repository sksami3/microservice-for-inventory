package com.example.product_service.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository _productRepository;

    public List<Product> getAllProducts() {
        try {
            return _productRepository.findByIsDeleteFalse();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving products: " + e.getMessage());
        }
    }

    public Product saveProduct(Product product) {
        try {
            return _productRepository.save(product);  // Save or update product
        } catch (Exception e) {
            throw new RuntimeException("Error saving product: " + e.getMessage());
        }
    }

    public Optional<Product> getProductById(Long id) {
        try {
            return _productRepository.findById(id);  // Find product by ID
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving product by ID: " + e.getMessage());
        }
    }

    public void softDeleteProduct(Long id) {
        try {
            _productRepository.softDeleteProduct(id);  // Soft delete the product by setting is_delete to true
        } catch (Exception e) {
            throw new RuntimeException("Error soft deleting product: " + e.getMessage());
        }
    }
}

