package com.receipt.processor.receiptprocessor.repository;

import com.receipt.processor.receiptprocessor.Entity.RetailerReceipt;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RetailerReceiptRepository {

    private static final ConcurrentHashMap<String, RetailerReceipt> storage = new ConcurrentHashMap<>();

    public void save(RetailerReceipt receipt) {
        storage.put(receipt.getId(), receipt);
    }

    public RetailerReceipt findById(String id) {
        if (storage.containsKey(id))
            return storage.get(id);
        else
            return null;
    }
}
