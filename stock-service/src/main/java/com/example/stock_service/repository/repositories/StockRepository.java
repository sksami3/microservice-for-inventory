package com.example.stock_service.repository.repositories;

import com.example.stock_service.model.Stock;
import com.example.stock_service.repository.interfaces.IStockRepository;
import com.example.stock_service.repository.repositories.base.BaseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockRepository extends BaseRepository<Stock> implements IStockRepository {
    @Override
    public Optional<Stock> findByProductId(long productId) {
        String query = "SELECT * FROM " + Stock.class.getSimpleName().toLowerCase() + " WHERE productId = ?";
        List<Stock> stocks = jdbcTemplate.query(query, new Object[]{productId}, new BeanPropertyRowMapper<>(Stock.class));

        if (stocks.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(stocks.get(0)); // Assuming you want the first match, adjust accordingly
        }
    }
}
