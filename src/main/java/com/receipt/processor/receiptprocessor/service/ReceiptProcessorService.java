package com.receipt.processor.receiptprocessor.service;

import com.receipt.processor.receiptprocessor.Entity.RetailerReceipt;
import com.receipt.processor.receiptprocessor.exceptions.CustomExceptionHandler;
import com.receipt.processor.receiptprocessor.mapping.ReceiptProcessorToRetailerMap;
import com.receipt.processor.receiptprocessor.pojo.request.Items;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;
import com.receipt.processor.receiptprocessor.pojo.response.GetPointsResponse;
import com.receipt.processor.receiptprocessor.pojo.response.ReceiptProcessorResponse;
import com.receipt.processor.receiptprocessor.repository.RetailerReceiptRepository;
import com.receipt.processor.receiptprocessor.utils.Constants;
import com.receipt.processor.receiptprocessor.utils.HelpUtils;
import com.receipt.processor.receiptprocessor.validations.RetailerReceiptValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReceiptProcessorService {

    @Autowired
    RetailerReceiptRepository retailerReceiptRepository;

    @Autowired
    RetailerReceiptValidation retailerReceiptValidation;


    public ReceiptProcessorResponse saveRetailerReceipt(ReceiptProcessRequest request) {
        ReceiptProcessorResponse response = new ReceiptProcessorResponse();
        RetailerReceipt receipt = ReceiptProcessorToRetailerMap.requestToEntity(request, processReceipt(request));
        retailerReceiptRepository.save(receipt);
        response.setId(receipt.getId());
        return response;
    }


    public long processReceipt(ReceiptProcessRequest request) {
        long points = 0;
        retailerReceiptValidation.validateRetailerReceiptRequest(request);
        points += request.getRetailer().chars() // Creates an IntStream
                .filter(Character::isLetterOrDigit) // Filters alphanumeric characters
                .count();

        BigDecimal total = new BigDecimal(request.getTotal());
        if (HelpUtils.isIntegerValue(total)) {
            points += 50;
        }
        if (total.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }
        points += 5 * (request.getItems().size() / 2);

        if (request.getItems() != null) {
            for (Items item : request.getItems()) {
                if (item.getShortDescription().trim().length() % 3 == 0) {
                    BigDecimal price = new BigDecimal(item.getPrice());
                    points += Math.ceil(price.multiply(new BigDecimal("0.2")).doubleValue());
                }
            }
        }
        try {
            LocalTime time = LocalTime.parse(request.getPurchaseTime(), Constants.timeFormat);
            if (HelpUtils.checkTime(time)) {
                points += 10;
            }
            LocalDate date = LocalDate.parse(request.getPurchaseDate(), Constants.dateFormat);
            if (date.getDayOfMonth() % 2 != 0) {
                points += 6;
            }
        } catch (DateTimeException e) {
            throw new RuntimeException(e);
        }
        return points;
    }


    public GetPointsResponse getPoints(String id) {
        GetPointsResponse response = new GetPointsResponse();
        RetailerReceipt receipt = retailerReceiptRepository.findById(id);
        if (receipt != null) {
            response.setPoints(receipt.getPoints());
        } else {
            throw new CustomExceptionHandler("Record not found with given Id");
        }
        return response;
    }


}
