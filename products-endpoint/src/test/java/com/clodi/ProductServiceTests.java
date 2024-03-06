package com.clodi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clodi.model.Product;
import com.clodi.repository.ProductRepository;
import com.clodi.service.ProductService;

/**
 * These are only examples of tests for learning reasons, and are in no way
 * thorough.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void updateProductSuccesfullyNoImage() {
		Product product = new Product();
		product.setId(1L);
		product.setName("mockProduct");
		product.setPrice(new BigDecimal(20));
		product.setImage("image.png");

		BDDMockito.given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		BDDMockito.given(productRepository.save(product)).willReturn(product);

		Product newProduct = new Product();
		newProduct.setName("mockProduct2");
		newProduct.setId(product.getId());
		newProduct.setPrice(product.getPrice());
		newProduct.setPrice(new BigDecimal(30));

		Optional<Product> returnedProductOpt = productService.updateProduct(newProduct);

		assertEquals(returnedProductOpt.get().getImage(), "image.png");
	}

	@Test
	void updateProductSuccesfullyWithImage() {
		Product product = new Product();
		product.setId(1L);
		product.setName("mockProduct");
		product.setPrice(new BigDecimal(20));
		product.setImage("image.png");

		BDDMockito.given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		BDDMockito.given(productRepository.save(product)).willReturn(product);

		Product newProduct = new Product();
		newProduct.setId(1L);
		newProduct.setName("mockProduct2");
		newProduct.setPrice(new BigDecimal(30));
		newProduct.setImage("image2.png");

		Optional<Product> returnedProductOpt = productService.updateProduct(newProduct);

		assertEquals(returnedProductOpt.get().getImage(), "image2.png");
	}

	@Test
	void updateProductIncompleteData() {
		Product product = new Product();
		product.setId(1L);
		product.setName("mockProduct");
		product.setPrice(new BigDecimal(20));
		product.setImage("image.png");

		Product newProduct = new Product();
		Optional<Product> returnedProductOpt = productService.updateProduct(newProduct);

		assertEquals(returnedProductOpt, Optional.empty());
	}

}
