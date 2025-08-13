package com.example.stock_service.repository.interfaces.base;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T> {
    void insert(T entity);
    void update(T entity, long id);
    Optional<T> findById(long id);
    List<T> findAll();
    void delete(Long id);
}
