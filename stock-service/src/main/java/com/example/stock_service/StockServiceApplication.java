package com.example.stock_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan("com.example.stock_service.model")
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
	}

}
