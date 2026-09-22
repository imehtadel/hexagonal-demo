package com.rightmove.hexagonaldemo.domain.service;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.domain.port.in.CreateQuoteUseCase;
import com.rightmove.hexagonaldemo.domain.port.in.GetQuoteUseCase;
import com.rightmove.hexagonaldemo.domain.port.out.QuoteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class QuoteService implements CreateQuoteUseCase, GetQuoteUseCase {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Quote createQuote(String customerName, BigDecimal premium) {
        return quoteRepository.save(Quote.createNew(customerName, premium));
    }

    @Override
    public Optional<Quote> getQuote(QuoteId id) {
        return quoteRepository.findById(id);
    }
}
