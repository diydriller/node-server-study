package com.spring.batch.mysql.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private String productDesc;
    private BigDecimal price;
    private Integer unit;
}
