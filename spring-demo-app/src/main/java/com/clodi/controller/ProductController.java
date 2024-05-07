package com.clodi.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.clodi.dto.ProductDTO;
import com.clodi.exception.ImageNotFoundException;
import com.clodi.exception.ProductNotFoundException;
import com.clodi.model.Product;
import com.clodi.model.ProductReceipt;
import com.clodi.proxy.ProductProxy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

@Controller public class ProductController {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    private final ProductProxy productProxy;

    public ProductController(ProductProxy productProxy) {
        this.productProxy = productProxy;
    }

    /**
     * Display all products in db.
     */
    @GetMapping("/products") public String showProducts(Model model) {
        LOGGER.info("Displaying products..");
        List<Product> products = productProxy.getAllProducts();

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{id}") public String showProduct(@PathVariable Long id, Model model) {
        LOGGER.info("Display product " + id);
        Optional<Product> productOpt = productProxy.getProduct(id);
        Product product = productOpt.orElseThrow(ProductNotFoundException::new);
        model.addAttribute("product", product);

        return "product";
    }

    /**
     * Show a simulated history of purchases (receipts).
     */
    @GetMapping("/products/history") public String getShowProductsHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        List<ProductReceipt> productReceipts = productProxy.getProductHistory(name);
        LOGGER.info("Displaying product history " + productReceipts);
        model.addAttribute("productReceipts", productReceipts);

        return "products-history";
    }

    @GetMapping("/products/add") public String getAddProductForm(Model model) {
        return "add-product-form";
    }

    @PostMapping(path = "/products/add") public String submitProduct(@ModelAttribute ProductDTO product,
                                                                     StandardMultipartHttpServletRequest request) {
        MultipartFile productFile = request.getFile("image");
        if (productFile != null && productFile.getOriginalFilename() != null) {
            String originalFilename = productFile.getOriginalFilename();
            product.setImageStr(originalFilename);
            ResponseEntity<Product> response = productProxy.addProduct(product);
            Product savedProduct = response.getBody();
            if (savedProduct != null) {
                productProxy.uploadImageFile(savedProduct.getId(), productFile);
            }
            else {
                throw new ImageNotFoundException();
            }
        }
        else {
            throw new ImageNotFoundException();
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit") public String editProductForm(Model model, @PathVariable Long id) {
        Optional<Product> productOpt = productProxy.getProduct(id);
        Product product = productOpt.orElseThrow(ProductNotFoundException::new);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getName());
        productDTO.setImageStr(product.getImage());
        model.addAttribute("product", productDTO);

        return "edit-product-form";
    }

    /**
     * Edit a product's name, price or image.
     */
    @PostMapping(path = "/products/{id}/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) public String editProductSubmit(
                    @ModelAttribute ProductDTO product, BindingResult bindingResult, StandardMultipartHttpServletRequest request) {

        if (product.getName() == null || product.getPrice() == null) {
            throw new IllegalStateException("Product name, price, id cannot be null.");
        }

        Long id = product.getId();
        // confirm that the id exists
        productProxy.getProduct(id).orElseThrow(ProductNotFoundException::new);

        MultipartFile productFile = request.getFile("image");
        if (productFile != null) {
            productProxy.uploadImageFile(id, productFile);
            product.setImageStr(productFile.getOriginalFilename());
        }
        productProxy.updateProduct(product);

        return "redirect:/products";
    }
}
