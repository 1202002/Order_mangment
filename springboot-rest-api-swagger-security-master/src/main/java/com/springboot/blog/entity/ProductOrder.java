package com.springboot.blog.entity;

import com.example.demo.entity.ProductOrderId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "product_order")
public class ProductOrder {
    @EmbeddedId
    private ProductOrderId id;

    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;

    // Getters and setters
    // ...
}