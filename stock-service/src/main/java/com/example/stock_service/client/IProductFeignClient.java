package com.example.stock_service.client;

import com.example.stock_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface IProductFeignClient {
    @GetMapping("/products/{productId}")
    ProductDTO getProductById(@PathVariable("productId") Long productId);
}
