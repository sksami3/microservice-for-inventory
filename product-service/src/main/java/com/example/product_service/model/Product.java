package com.example.product_service.model;

import com.example.product_service.model.Base.BaseModel;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
}
