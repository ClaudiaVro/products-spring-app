package com.clodi.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clodi.bucket.BucketName;
import com.clodi.model.Product;
import com.clodi.repository.ProductRepository;

@Service
public class ProductService {

	private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

	private final ProductRepository productRepository;
	private final AmazonFileStoreService amazonFileStoreService;

	public ProductService(ProductRepository productRepository, AmazonFileStoreService amazonFileStoreService) {
		this.productRepository = productRepository;
		this.amazonFileStoreService = amazonFileStoreService;
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public Optional<Product> updateProduct(Product newProduct) {
		if (newProduct.getId() == null || newProduct.getPrice() == null || newProduct.getName() == null) {
			return Optional.empty();
		}

		Optional<Product> productOpt = getProductById(newProduct.getId());
		return productOpt.map(p -> {
			p.setName(newProduct.getName());
			p.setPrice(newProduct.getPrice());
			if (newProduct.getImage() != null) {
				p.setImage(newProduct.getImage());
			}
			return productRepository.save(p);
		});
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public byte[] downloadProductImage(Long id) {
		Optional<Product> productOpt = productRepository.findById(id);
		return productOpt.map(product -> {
			String path = String.format("%s/%s", BucketName.PRODUCT_IMAGES.getBucketName(), product.getId());
			LOGGER.info(path + " " + product.getImage());
			return amazonFileStoreService.download(path, product.getImage());
		}).orElseGet(() -> new byte[0]);
	}

	public void uploadProductImage(Long id, MultipartFile file) {
		if (file.isEmpty()) {
			throw new IllegalStateException("file is empty");
		}
		String contentType = file.getContentType();
		List<String> contentTypes = Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),
				ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_WEBP.getMimeType());
		if (!contentTypes.contains(contentType)) {
			throw new IllegalStateException("unacceptable mime type");
		}
		Optional<Product> productOpt = getProductById(id);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			Map<String, String> metadata = new HashMap<>();
			metadata.put("Content-Type", file.getContentType());
			metadata.put("Content-Length", String.valueOf(file.getSize()));

			String path = String.format("%s/%s", BucketName.PRODUCT_IMAGES.getBucketName(), product.getId());
			try {
				amazonFileStoreService.save(path, file.getOriginalFilename(), Optional.of(metadata),
						file.getInputStream());
			} catch (IOException e) {
				throw new IllegalStateException();
			}
		}

	}
}
