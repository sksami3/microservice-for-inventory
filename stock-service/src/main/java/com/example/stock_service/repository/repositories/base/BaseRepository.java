package com.example.stock_service.repository.repositories.base;

import com.example.stock_service.repository.interfaces.base.IBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BaseRepository<T> implements IBaseRepository<T> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    @Override
    public void insert(T entity) {
        if (entity == null) {
            logger.error("Entity is null! Unable to perform save operation.");
            return;
        }

        try {
            // Perform insert (Replace this with actual insert query and logic)
            String insertQuery = "INSERT INTO " + getEntityTableName() + " (productId, quantity) VALUES (?,?)";
            jdbcTemplate.update(insertQuery,
                    getFieldValue(entity, "productId"),
                    getFieldValue(entity, "quantity"));
            logger.info("Entity saved successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while saving entity: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(T entity, long id) {
        if (entity == null) {
            logger.error("Entity is null! Unable to perform update operation.");
            return;
        }

        try {
            Optional<T> object = findById(id);
            if (object.isPresent()) {
                id = (Long) getFieldValue(object.get(), "id");
                String updateQuery = "UPDATE " + getEntityTableName() + " SET quantity = ? WHERE id = ?";
                jdbcTemplate.update(updateQuery, getFieldValue(entity, "quantity"), id);
                logger.info("Entity with ID {} updated successfully.", id);
            } else {
                logger.warn("Entity with ID {} not found. Update operation skipped.", id);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating entity: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<T> findById(long id) {
        String query = "SELECT * FROM " + getEntityTableName() + " WHERE id = ?";
        T entity = jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(getEntityClass()));

        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        String query = "SELECT * FROM " + getEntityTableName();
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(getEntityClass()));
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM " + getEntityTableName() + " WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public Object getFieldValue(T entity, String fieldName) {
        try {
            Field field = null;
            // Check in the current class
            try {
                field = entity.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Check in the base class (parent class)
                field = entity.getClass().getSuperclass().getDeclaredField(fieldName);
            }

            // Make sure the field is accessible
            field.setAccessible(true);
            return field.get(entity);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Error accessing field: " + fieldName, e);
            return null;
        }
    }


    private String getEntityTableName() {
        // Assuming the table name is the lowercase name of the class (e.g., "stock" for Stock class)
        return getEntityClass().getSimpleName().toLowerCase();
    }

    private Class<T> getEntityClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}
