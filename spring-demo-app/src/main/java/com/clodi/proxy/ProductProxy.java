package com.clodi.proxy;

import java.util.List;
import java.util.Optional;

import com.clodi.config.SecurityConfig;
import com.clodi.dto.ProductDTO;
import com.clodi.dto.ProductReceiptDTO;
import com.clodi.model.Product;
import com.clodi.model.ProductReceipt;
import com.clodi.model.Sale;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "product", url = "${name.product.url}", configuration = SecurityConfig.class) public interface ProductProxy {

    @GetMapping("/products") List<Product> getAllProducts();

    @GetMapping("products/{id}") Optional<Product> getProduct(@PathVariable Long id);

    @GetMapping("/receipts/{customerName}") List<ProductReceipt> getProductHistory(@RequestHeader String customerName);

    @PostMapping("/receipts/addReceipt") ResponseEntity<ProductReceipt> addReceipt(ProductReceiptDTO productReceipt);

    @GetMapping("/sales") List<Sale> getAllSales();

    @PostMapping(path = "/products") ResponseEntity<Product> addProduct(ProductDTO product);

    @PostMapping(path = "/products/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) void uploadImageFile(
                    @RequestParam Long id, @RequestPart(value = "file") MultipartFile file);

    @PutMapping("/products") Optional<ProductDTO> updateProduct(@RequestBody ProductDTO newProduct);

}
