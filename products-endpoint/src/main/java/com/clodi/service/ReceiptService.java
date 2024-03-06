package com.clodi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clodi.model.ProductReceipt;
import com.clodi.model.Sale;
import com.clodi.repository.ReceiptRepository;
import com.clodi.repository.SaleRepository;

@Service
public class ReceiptService {

	private final ReceiptRepository receiptRepository;
	private final SaleRepository saleRepository;

	public ReceiptService(ReceiptRepository receiptRepository, SaleRepository saleRepository) {
		this.receiptRepository = receiptRepository;
		this.saleRepository = saleRepository;
	}

	public List<ProductReceipt> getAllReceipts() {
		List<ProductReceipt> findAll = receiptRepository.findAll();
		return findAll;
	}

	public List<ProductReceipt> getReceiptsByCustomer(String name) {
		return receiptRepository.findByCustomerName(name);
	}

	public List<Sale> getSales() {
		List<Sale> findAll = saleRepository.findAll();
		return findAll;
	}
}
