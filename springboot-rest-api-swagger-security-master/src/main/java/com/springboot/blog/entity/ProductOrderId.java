
package com.springboot.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ProductOrderId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;

}