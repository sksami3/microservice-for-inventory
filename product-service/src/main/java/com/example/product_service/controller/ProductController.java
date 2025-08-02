package com.example.product_service.controller;

import com.example.product_service.model.Product;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService _productService;

    // Endpoint to get all products (not soft deleted)
    @GetMapping
    public List<Product> getAllProducts() {
        return _productService.getAllProducts();  // Get all non-deleted products
    }

    // Endpoint to get a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return _productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Endpoint to save or update a product
    @PostMapping
    public Product saveProduct(@RequestBody Product product)
    {
        return  _productService.saveProduct(product);
    }

    // Endpoint to soft delete a product
    @DeleteMapping("/{id}")
    public void softDeleteProduct(@PathVariable Long id) {
        _productService.softDeleteProduct(id);  // Soft delete product by ID
    }
}
