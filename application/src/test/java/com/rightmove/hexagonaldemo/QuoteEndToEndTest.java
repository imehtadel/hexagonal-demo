package com.rightmove.hexagonaldemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsAQuoteThenReadsItBackThroughTheFullStack() throws Exception {
        String responseBody = mockMvc.perform(post("/quotes")
                        .contentType("application/json")
                        .content("""
                                {"customerName": "Jane Doe", "premium": 199.99}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"))
                .andReturn().getResponse().getContentAsString();

        String id = responseBody.split("\"id\":\"")[1].split("\"")[0];

        mockMvc.perform(get("/quotes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"));
    }

    @Test
    void returnsNotFoundForAnUnknownQuote() throws Exception {
        mockMvc.perform(get("/quotes/{id}", "00000000-0000-0000-0000-000000000000"))
                .andExpect(status().isNotFound());
    }
}
