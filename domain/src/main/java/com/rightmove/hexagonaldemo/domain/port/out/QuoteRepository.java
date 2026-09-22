package com.rightmove.hexagonaldemo.domain.port.out;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;

import java.util.Optional;

public interface QuoteRepository {

    Quote save(Quote quote);

    Optional<Quote> findById(QuoteId id);
}
