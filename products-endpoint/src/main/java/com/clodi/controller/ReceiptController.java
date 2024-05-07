package com.clodi.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.clodi.dto.ProductReceiptDTO;
import com.clodi.model.ProductReceipt;
import com.clodi.model.Sale;
import com.clodi.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController public class ReceiptController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/receipts") public List<ProductReceipt> getReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/sales") public List<Sale> getSales() {
        return receiptService.getSales();
    }

    @GetMapping("/receipts/{customerName}") public List<ProductReceipt> getProductHistory(@RequestHeader String customerName) {
        List<ProductReceipt> receiptsByCustomer = receiptService.getReceiptsByCustomer(customerName);
        log.info("Retrieving customer receipts " + receiptsByCustomer);
        return receiptsByCustomer;
    }

    @PostMapping("/receipts/addReceipt") ResponseEntity<ProductReceipt> addReceipt(@RequestBody ProductReceiptDTO productReceiptDTO) {
        ProductReceipt productReceipt = new ProductReceipt();
        productReceipt.setDateTime(productReceiptDTO.getDateTime());
        productReceipt.setCustomerName(productReceiptDTO.getCustomerName());
        Stream<Sale> saleStream = productReceiptDTO.getSales().stream().map(dto -> {
            Sale sale = new Sale();
            sale.setProduct(dto.getProduct());
            sale.setProductReceipt(productReceipt);
            return sale;
        });
        List<Sale> sales = saleStream.collect(Collectors.toList());
        productReceipt.setSales(sales);

        ProductReceipt savedReceipt = receiptService.saveReceipt(productReceipt);
        if (savedReceipt == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }
}
