package com.clodi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clodi.model.ProductReceipt;
import com.clodi.model.Sale;
import com.clodi.service.ReceiptService;

@RestController
public class ReceiptController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final ReceiptService receiptService;

	public ReceiptController(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	@GetMapping("/receipts")
	public List<ProductReceipt> getReceipts() {
		return receiptService.getAllReceipts();
	}

	@GetMapping("/sales")
	public List<Sale> getSales() {
		return receiptService.getSales();
	}

	@GetMapping("/receipts/{customerName}")
	public List<ProductReceipt> getReceiptsByCustomer(@PathVariable String customerName) {
		List<ProductReceipt> receiptsByCustomer = receiptService.getReceiptsByCustomer(customerName);
		log.info("Retrieving customer receipts " + receiptsByCustomer);
		return receiptsByCustomer;
	}

}
