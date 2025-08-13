package com.example.stock_service.repository.interfaces;

import com.example.stock_service.model.Stock;
import com.example.stock_service.repository.interfaces.base.IBaseRepository;

import java.util.Optional;

public interface IStockRepository extends IBaseRepository<Stock> {
    Optional<Stock> findByProductId(long id);
}
