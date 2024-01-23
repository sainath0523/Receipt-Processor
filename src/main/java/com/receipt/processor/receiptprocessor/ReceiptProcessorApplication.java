package com.receipt.processor.receiptprocessor;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Receipt Processor API", version = "1.0", description = " Receipt Processor Challenge APIs"))
public class ReceiptProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptProcessorApplication.class, args);
    }

}
