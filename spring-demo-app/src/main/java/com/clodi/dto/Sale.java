package com.clodi.dto;

public class Sale {

	private Long id;
	private ProductDTO product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", product=" + product + "]";
	}

}
