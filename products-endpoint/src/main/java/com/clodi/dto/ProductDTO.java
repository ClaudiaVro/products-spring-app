package com.clodi.dto;

import java.math.BigDecimal;

/**
 * @author Claudia Vidican
 */
public class ProductDTO {

    private String name;
    private BigDecimal price;
    private String imageStr;

    public ProductDTO() {
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