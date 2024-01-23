package com.receipt.processor.receiptprocessor.controller;

import com.receipt.processor.receiptprocessor.exceptions.CustomExceptionHandler;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;
import com.receipt.processor.receiptprocessor.pojo.response.GetPointsResponse;
import com.receipt.processor.receiptprocessor.pojo.response.ReceiptProcessorResponse;
import com.receipt.processor.receiptprocessor.service.ReceiptProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/receipt-processor/v1/")
public class ReceiptProcessorController {

    @Autowired
    ReceiptProcessorService receiptProcessorService;

    @PostMapping(value = "/receipts/process", consumes = "application/json", produces = "application/json")
    public ReceiptProcessorResponse saveRetailerReceipt(@RequestBody ReceiptProcessRequest request) {
        try {
            return receiptProcessorService.saveRetailerReceipt(request);
        } catch (Exception e) {
            throw new CustomExceptionHandler(e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/receipts/{id}/points", produces = "application/json")
    public GetPointsResponse getPoints(@PathVariable("id") String id) {
        try {
            if (id.isBlank()) {
                throw new CustomExceptionHandler("{id} is Empty/Blank");
            }
            return receiptProcessorService.getPoints(id);
        } catch (Exception e) {
            throw new CustomExceptionHandler(e.getLocalizedMessage());
        }
    }

}
