package com.clodi.model;

public class Sale {

    private Long id;
    private Product product;

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

    @Override public String toString() {
        return "Sale [id=" + id + ", product=" + product + "]";
    }

}
