package com.example.stock_service.controller;

import com.example.stock_service.client.IProductFeignClient;
import com.example.stock_service.model.Stock;
import com.example.stock_service.service.interfaces.IStockService;
import com.example.stock_service.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private IStockService stockService;

    @Autowired
    private IProductFeignClient productFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    // Save or update stock
    @PostMapping
    public ResponseEntity<String> saveStock(@RequestBody Stock stock) {
        Optional<Stock> savedStock = stockService.getStockByProductId(stock.getProductId());

        if (savedStock.isPresent()) {
            stockService.updateStock(stock, savedStock.get().getId());
            logger.info("Stock updated for productId: {}", stock.getProductId());
            return ResponseEntity.ok("Stock updated successfully.");
        } else {
            stockService.insertStock(stock);
            logger.info("New stock inserted for productId: {}", stock.getProductId());
            return ResponseEntity.status(HttpStatus.CREATED).body("Stock added successfully.");
        }
    }

    // Get stock and product details by productId
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getStockByProductId(@PathVariable Long productId) {
        Optional<Stock> stock = stockService.getStockByProductId(productId);

        if (stock.isPresent()) {
            try {
                // Fetch product details using Feign client
                ProductDTO product = productFeignClient.getProductById(productId);

                if (product != null) {
                    product.setQuantity(stock.get().getQuantity());
                    logger.info("Product details fetched for productId: {}", productId);
                    return ResponseEntity.ok(product);
                } else {
                    logger.warn("Product not found for productId: {}", productId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
            } catch (Exception e) {
                logger.error("Error fetching product details for productId: {}", productId, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            logger.warn("Stock not found for productId: {}", productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all stocks
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        if (stocks.isEmpty()) {
            logger.info("No stocks found.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stocks);
        }
        return ResponseEntity.ok(stocks);
    }

    // Delete stock by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockByProductId(id);
        if (stock.isPresent()) {
            stockService.deleteStock(id);
            logger.info("Stock with id {} deleted successfully", id);
            return ResponseEntity.ok("Stock deleted successfully.");
        } else {
            logger.warn("Stock with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found.");
        }
    }
}