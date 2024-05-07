package com.clodi.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private String imageStr;

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
