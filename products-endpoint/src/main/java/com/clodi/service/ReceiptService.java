package com.clodi.service;

import java.util.List;

import com.clodi.model.ProductReceipt;
import com.clodi.model.Sale;
import com.clodi.dao.ReceiptRepository;
import com.clodi.dao.SaleRepository;
import org.springframework.stereotype.Service;

@Service public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final SaleRepository saleRepository;

    public ReceiptService(ReceiptRepository receiptRepository, SaleRepository saleRepository) {
        this.receiptRepository = receiptRepository;
        this.saleRepository = saleRepository;
    }

    public List<ProductReceipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public List<ProductReceipt> getReceiptsByCustomer(String name) {
        return receiptRepository.findByCustomerName(name);
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public ProductReceipt saveReceipt(ProductReceipt productReceipt) {
        ProductReceipt savedProductReceipt = receiptRepository.save(productReceipt);
        List<Sale> sales = savedProductReceipt.getSales();
        saleRepository.saveAll(sales);
        return savedProductReceipt;
    }
}
