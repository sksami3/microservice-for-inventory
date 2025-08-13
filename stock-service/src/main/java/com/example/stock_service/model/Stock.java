package com.example.stock_service.model;

import com.example.stock_service.model.Base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock extends BaseModel {
    private long productId;
    private int quantity;
}
