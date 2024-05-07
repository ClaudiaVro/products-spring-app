package com.clodi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

/**
 * @author Claudia Vidican
 */
public class ProductReceiptDTO {

    private List<SaleDTO> sales;
    @NotEmpty private String customerName;
    private LocalDateTime dateTime;

    public ProductReceiptDTO() {
    }

    public List<SaleDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleDTO> sales) {
        this.sales = sales;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
