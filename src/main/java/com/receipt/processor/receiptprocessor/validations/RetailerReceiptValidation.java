package com.receipt.processor.receiptprocessor.validations;

import com.receipt.processor.receiptprocessor.exceptions.CustomExceptionHandler;
import com.receipt.processor.receiptprocessor.pojo.request.Items;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;
import com.receipt.processor.receiptprocessor.utils.Constants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

@Component
public class RetailerReceiptValidation {

    public void validateRetailerReceiptRequest(ReceiptProcessRequest request) {
        try {
            Constants.dateFormat.parse(request.getPurchaseDate());
        } catch (DateTimeParseException e) {
            throw new CustomExceptionHandler("Purchase Date not Valid");
        }
        try {
            Constants.timeFormat.parse(request.getPurchaseTime());
        } catch (DateTimeParseException e) {
            throw new CustomExceptionHandler("Purchase Time not Valid");
        }
        BigDecimal total;
        try {
            total = new BigDecimal(request.getTotal());
        } catch (NumberFormatException e) {

            throw new CustomExceptionHandler("total not Valid");
        }

        BigDecimal sum = new BigDecimal("0.0");

        try {
            if (!(request.getItems() != null && request.getItems().isEmpty())) {
                sum = request.getItems().stream().map(item -> new BigDecimal(item.getPrice()))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                if (request.getItems().stream().map(Items::getShortDescription).filter(k -> k.isBlank()).count() > 0) {
                    throw new CustomExceptionHandler("Short Description is Empty/Blank");
                }

            }
        } catch (Exception e) {
            throw new CustomExceptionHandler(e.getLocalizedMessage());
        }
        if (sum.compareTo(total) != 0) {
            throw new CustomExceptionHandler("Total is not matching with the sum of given items price");
        }

    }
}
