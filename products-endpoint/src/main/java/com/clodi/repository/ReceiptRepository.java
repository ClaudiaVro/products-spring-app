package com.clodi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clodi.model.ProductReceipt;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<ProductReceipt, Long> {

    List<ProductReceipt> findByCustomerName(String customerName);
}
