package com.clodi.controller;

import java.util.List;
import java.util.Optional;

import com.clodi.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clodi.annotation.ProductAnalyticsLog;
import com.clodi.model.Product;
import com.clodi.service.ProductService;

import javax.validation.Valid;

@RestController
public class ProductController {

	@Value("${uploadDir}")
	private String uploadFolder;

	private final ProductService productService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public List<Product> showAllProducts() {
		return productService.getProducts();
	}

	@ProductAnalyticsLog
	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable Long id) {
		Optional<Product> productOpt = productService.getProductById(id);
		return productOpt;
	}

	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductDTO productDTO) {

		// TODO: in exception handling, also add the product dto valid data, so I can repopulate
		// TODO: return Product only
		Product product = new Product();
		product.setImage(productDTO.getImageStr());
		product.setPrice(productDTO.getPrice());
		product.setName(productDTO.getName());
		Product savedProduct = productService.saveProduct(product);

		ResponseEntity<Product> responseEntity = new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/products")
	public Optional<Product> updateProduct(@RequestBody Product newProduct) {
		return productService.updateProduct(newProduct);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/products/{id}/image")
	public byte[] downloadProductImage(@PathVariable Long id) {
		return productService.downloadProductImage(id);
	}

	@PostMapping(path = "/products/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void uploadProductImage(@RequestParam Long id, @RequestPart(value = "file") MultipartFile file) {
		log.debug("uploading from endpoint " + id + " " + file);
		productService.uploadProductImage(id, file);
	}

}
