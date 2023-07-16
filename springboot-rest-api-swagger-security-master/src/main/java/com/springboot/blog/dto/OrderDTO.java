package com.example.demo.dto;

import java.time.LocalDate;




public class OrderDTO {
    private Long id;
    private Long customerId;
    private LocalDate orderAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDate orderAt) {
        this.orderAt = orderAt;
    }
}