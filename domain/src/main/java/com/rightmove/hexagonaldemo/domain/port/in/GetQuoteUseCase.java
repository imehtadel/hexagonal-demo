package com.rightmove.hexagonaldemo.domain.port.in;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;

import java.util.Optional;

public interface GetQuoteUseCase {

    Optional<Quote> getQuote(QuoteId id);
}
