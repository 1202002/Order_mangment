package com.example.demo.repository;

import com.example.demo.entity.ProductOrder;
import com.example.demo.entity.ProductOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
    // Custom methods, if needed
}