package com.receipt.processor.receiptprocessor.mapping;

import com.receipt.processor.receiptprocessor.Entity.Item;
import com.receipt.processor.receiptprocessor.Entity.RetailerReceipt;
import com.receipt.processor.receiptprocessor.pojo.request.Items;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;
import com.receipt.processor.receiptprocessor.utils.Constants;
import com.receipt.processor.receiptprocessor.utils.HelpUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReceiptProcessorToRetailerMap {

    public static RetailerReceipt requestToEntity(ReceiptProcessRequest request, long points) {
        RetailerReceipt receipt = new RetailerReceipt();
        if (receipt.getItems() != null) {
            receipt.setItems(itemsMapping(request.getItems()));
        }
        receipt.setPoints(points);
        receipt.setTotal(new BigDecimal(request.getTotal()));
        receipt.setRetailer(request.getRetailer());
        receipt.setPurchaseDate(LocalDate.parse(request.getPurchaseDate(), Constants.dateFormat));
        receipt.setPurchaseTime(LocalTime.parse(request.getPurchaseTime(), Constants.timeFormat));
        receipt.setId(HelpUtils.generateUniqueId());
        return receipt;

    }

    public static List<Item> itemsMapping(List<Items> items) {
        List<Item> itemsResponse = new ArrayList<>();
        for (Items item : items) {
            Item resItem = new Item();
            resItem.setPrice(new BigDecimal(item.getPrice()));
            resItem.setShortDescription(item.getShortDescription());
            itemsResponse.add(resItem);
        }
        return itemsResponse;
    }
}
