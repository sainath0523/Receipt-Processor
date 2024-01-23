package com.receipt.processor.receiptprocessor.pojo.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiptProcessRequest {

    @NotNull(message = "is Required")
    @NotBlank(message = "is Required")
    private String retailer;
    @NotNull(message = "is Required")
    private String purchaseDate;
    @NotNull(message = "is Required")
    private String purchaseTime;
    private List<Items> items;
    @NotNull(message = "is Required")
    private String total;
}
