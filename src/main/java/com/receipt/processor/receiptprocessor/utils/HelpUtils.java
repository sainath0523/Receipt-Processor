package com.receipt.processor.receiptprocessor.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;

public class HelpUtils {

    public static boolean isIntegerValue(BigDecimal num) {
        return num.scale() <= 0 || num.stripTrailingZeros().scale() <= 0;
    }

    public static boolean checkTime(LocalTime time) {
        LocalTime start = LocalTime.of(14, 0);
        LocalTime end = LocalTime.of(16, 0);
        return !time.isBefore(start) && time.isBefore(end);
    }

    public static String generateUniqueId() {
        long currentTimeNano = System.nanoTime();
        long threadId = Thread.currentThread().getId();
        Instant now = Instant.now();

        String id = now.toString() + "-" + currentTimeNano + "-" + threadId;
        return id;
    }

}
