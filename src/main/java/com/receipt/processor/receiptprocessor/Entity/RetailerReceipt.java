package com.receipt.processor.receiptprocessor.Entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RetailerReceipt {

    private String id;

    private String retailer;

    private LocalDate purchaseDate;

    private LocalTime purchaseTime;

    private List<Item> items;

    private BigDecimal total;

    private Long points;
}
