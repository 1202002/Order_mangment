package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductOrderDTO {
    private Long productId;
    private Long orderId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;

}