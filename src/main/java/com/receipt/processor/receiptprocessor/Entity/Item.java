package com.receipt.processor.receiptprocessor.Entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Item {

    private String shortDescription;

    private BigDecimal price;
}
