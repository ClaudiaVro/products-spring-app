package com.clodi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "sale") public class Sale {

    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id private Long id;

    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "receipt_id") @JsonBackReference private ProductReceipt productReceipt;

    @OneToOne @JoinColumn(name = "product_id") private Product product;

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductReceipt getProductReceipt() {
        return productReceipt;
    }

    public void setProductReceipt(ProductReceipt productReceipt) {
        this.productReceipt = productReceipt;
    }

    @Override public String toString() {
        return "Sale [id=" + id + ", product=" + product + "]";
    }

}
