package com.receipt.processor.receiptprocessor;

import com.receipt.processor.receiptprocessor.pojo.request.Items;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;

import java.util.List;

public class HelpUtils {

    public static ReceiptProcessRequest getRequest() {
        ReceiptProcessRequest request = new ReceiptProcessRequest();
        request.setTotal("10");
        request.setRetailer("Example");
        request.setPurchaseTime("15:09");
        request.setPurchaseDate("2022-01-01");
        Items items = new Items();
        items.setPrice("10");
        items.setShortDescription("Description");
        request.setItems(List.of(items));

        return request;
    }
}
