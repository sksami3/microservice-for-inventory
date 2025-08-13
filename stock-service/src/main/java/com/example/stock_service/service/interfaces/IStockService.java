package com.example.stock_service.service.interfaces;

import com.example.stock_service.model.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    void insertStock(Stock stock);
    void updateStock(Stock stock, long id);
    Optional<Stock> getStockById(Long id);
    List<Stock> getAllStocks();
    void deleteStock(Long id);
    Optional<Stock> getStockByProductId(Long productId);
}
