package com.clodi.model;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String name;

    private BigDecimal price;

    private String image;

    public Product(Long id, String name, BigDecimal price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageLink() {
        String prefix = "http://localhost:9000/products";
        String suffix = "image";

        return String.format("%s/%d/%s", prefix, id, suffix);
    }

}
