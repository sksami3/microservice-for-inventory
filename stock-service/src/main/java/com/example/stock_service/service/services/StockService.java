package com.example.stock_service.service.services;

import com.example.stock_service.model.Stock;
import com.example.stock_service.repository.interfaces.IStockRepository;
import com.example.stock_service.service.interfaces.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements IStockService {
    @Autowired
    private IStockRepository _stockRepository;

    @Override
    public void insertStock(Stock stock) {
        _stockRepository.insert(stock);
    }

    @Override
    public void updateStock(Stock stock, long id) {
        _stockRepository.update(stock, id);
    }

    @Override
    public Optional<Stock> getStockById(Long id) {
        return _stockRepository.findById(id);
    }

    @Override
    public List<Stock> getAllStocks() {
        return _stockRepository.findAll();
    }

    @Override
    public void deleteStock(Long id) {
        _stockRepository.delete(id);
    }

    @Override
    public Optional<Stock> getStockByProductId(Long productId) {
        return _stockRepository.findByProductId(productId);
    }
}
