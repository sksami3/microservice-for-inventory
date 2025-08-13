package com.example.stock_service.model.Base;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
