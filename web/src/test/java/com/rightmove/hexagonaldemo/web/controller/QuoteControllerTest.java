package com.rightmove.hexagonaldemo.web.controller;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.domain.port.in.CreateQuoteUseCase;
import com.rightmove.hexagonaldemo.domain.port.in.GetQuoteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateQuoteUseCase createQuoteUseCase;

    @MockitoBean
    private GetQuoteUseCase getQuoteUseCase;

    private Quote quote;

    @BeforeEach
    void setUp() {
        quote = Quote.createNew("Jane Doe", BigDecimal.valueOf(199.99));
    }

    @Test
    void createsAQuote() throws Exception {
        when(createQuoteUseCase.createQuote(eq("Jane Doe"), any())).thenReturn(quote);

        mockMvc.perform(post("/quotes")
                        .contentType("application/json")
                        .content("""
                                {"customerName": "Jane Doe", "premium": 199.99}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"));
    }

    @Test
    void rejectsAnInvalidRequest() throws Exception {
        mockMvc.perform(post("/quotes")
                        .contentType("application/json")
                        .content("""
                                {"customerName": "", "premium": -1}
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void returnsAQuoteById() throws Exception {
        when(getQuoteUseCase.getQuote(quote.id())).thenReturn(Optional.of(quote));

        mockMvc.perform(get("/quotes/{id}", quote.id().value()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"));
    }

    @Test
    void returnsNotFoundWhenQuoteIsMissing() throws Exception {
        QuoteId missingId = QuoteId.generate();
        when(getQuoteUseCase.getQuote(missingId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/quotes/{id}", missingId.value()))
                .andExpect(status().isNotFound());
    }
}
