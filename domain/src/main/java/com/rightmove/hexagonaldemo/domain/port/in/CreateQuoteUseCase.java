package com.rightmove.hexagonaldemo.domain.port.in;

import com.rightmove.hexagonaldemo.domain.model.Quote;

import java.math.BigDecimal;

public interface CreateQuoteUseCase {

    Quote createQuote(String customerName, BigDecimal premium);
}
