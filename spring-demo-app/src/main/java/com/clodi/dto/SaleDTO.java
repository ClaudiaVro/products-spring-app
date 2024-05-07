package com.clodi.dto;

import com.clodi.model.Product;

public class SaleDTO {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override public String toString() {
        return "SaleDTO{" + "product=" + product + '}';
    }
}
