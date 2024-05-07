package com.clodi.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.clodi.dto.ProductReceiptDTO;
import com.clodi.dto.SaleDTO;
import com.clodi.model.Product;
import com.clodi.model.ProductReceipt;
import com.clodi.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Claudia Vidican
 */
@Controller @SessionAttributes("productReceiptDTO") public class ProductReceiptController {

    @Autowired private ProductProxy productProxy;

    @ModelAttribute public void addProducts(Model model) {
        List<Product> products = productProxy.getAllProducts();
        model.addAttribute("products", products);
    }

    @GetMapping("/receipts/addReceipt") public String getAddReceiptForm(ProductReceiptDTO productReceiptDTO, Model model) {
        model.addAttribute(productReceiptDTO);
        return "add-receipt-form";
    }

    @PostMapping(path = "/receipts/addReceipt", params = { "submit" }) public String submitReceipt(ProductReceiptDTO productReceiptDTO) {
        List<SaleDTO> sales = productReceiptDTO.getSales();
        if (sales == null || sales.isEmpty()) {
            throw new RuntimeException("There were no sales performed!");
        }

        productReceiptDTO.setDateTime(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        productReceiptDTO.setCustomerName(name);

        ResponseEntity<ProductReceipt> response = productProxy.addReceipt(productReceiptDTO);
        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Could not add product receipt " + productReceiptDTO + " " + response);
        }
        return "add-receipt-form";
    }

    @PostMapping(path = "/receipts/addReceipt", params = { "reset" }) public String resetReceipt(ProductReceiptDTO productReceiptDTO) {
        if (productReceiptDTO.getSales() == null || productReceiptDTO.getSales().isEmpty()) {
            return "add-receipt-form";
        }
        productReceiptDTO.getSales().clear();
        return "add-receipt-form";
    }

    @PostMapping(path = "/receipts/addReceipt", params = { "addProductId" }) public String addProductToReceipt(ProductReceiptDTO productReceiptDTO,
                                                                                                               @RequestParam Long addProductId) {
        List<SaleDTO> sales = productReceiptDTO.getSales();
        if (sales == null) {
            sales = new ArrayList<>();
        }
        Optional<Product> product = productProxy.getProduct(addProductId);
        if (product.isPresent()) {
            SaleDTO sale = new SaleDTO();
            sale.setProduct(product.get());
            sales.add(sale);
            productReceiptDTO.setSales(sales);
            return "add-receipt-form";
        }
        else {
            throw new RuntimeException("Couldn't find product for specified id " + addProductId);
        }
    }

    @PostMapping(path = "/receipts/addReceipt", params = { "removeProductId" }) public String removeProductFromReceipt(
                    ProductReceiptDTO productReceiptDTO, @RequestParam Long removeProductId) {
        List<SaleDTO> sales = productReceiptDTO.getSales();
        List<SaleDTO> newSales = sales.stream().filter(s -> Objects.equals(s.getProduct().getId(), removeProductId)).toList();
        sales.removeAll(newSales);
        return "add-receipt-form";
    }
}
