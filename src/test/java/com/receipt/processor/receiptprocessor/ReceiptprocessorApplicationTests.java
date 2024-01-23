package com.receipt.processor.receiptprocessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.receipt.processor.receiptprocessor.pojo.request.ReceiptProcessRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ReceiptprocessorApplicationTests {


    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void processReceipt_thenStatus200() throws Exception {

        mockMvc.perform(post("/api/receipt-processor/v1/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(HelpUtils.getRequest())))
                .andExpect(status().isOk());
    }

    @Test
    void processReceipt_thenStatus400() throws Exception {

        ReceiptProcessRequest request = HelpUtils.getRequest();
        request.setRetailer("");
        mockMvc.perform(post("/api/receipt-processor/v1/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPoints_thenStatus200() throws Exception {

        String responseString = mockMvc.perform(post("/api/receipt-processor/v1/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(HelpUtils.getRequest())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode responseJson = objectMapper.readTree(responseString);
        String id = responseJson.get("id").asText();
        mockMvc.perform(get("/api/receipt-processor/v1/receipts/" + id + "/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.points").value(98));
    }

    @Test
    void getPoints_thenStatus400() throws Exception {

        mockMvc.perform(get("/api/receipt-processor/v1/receipts/dummy/points"))
                .andExpect(status().isBadRequest());
    }

}
