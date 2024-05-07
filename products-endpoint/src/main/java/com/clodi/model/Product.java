package com.clodi.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "product") public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false, unique = true) private Long id;

    @Column(name = "name", nullable = false) private String name;

    @Column(name = "price", precision = 10, scale = 2) private BigDecimal price;

    @Column(name = "image") private String image;

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

    @Override public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + "]";
    }

}
