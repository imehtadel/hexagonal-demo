package com.rightmove.hexagonaldemo.domain.service;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.domain.port.out.QuoteRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class QuoteServiceTest {

    private final InMemoryQuoteRepository repository = new InMemoryQuoteRepository();
    private final QuoteService quoteService = new QuoteService(repository);

    @Test
    void createsAndPersistsAQuote() {
        Quote quote = quoteService.createQuote("Jane Doe", BigDecimal.valueOf(199.99));

        assertThat(quote.customerName()).isEqualTo("Jane Doe");
        assertThat(quoteService.getQuote(quote.id())).contains(quote);
    }

    @Test
    void returnsEmptyWhenQuoteDoesNotExist() {
        assertThat(quoteService.getQuote(QuoteId.generate())).isEmpty();
    }

    /** Hand-rolled fake outbound port — no Spring, no database, proves the domain is adapter-agnostic. */
    private static class InMemoryQuoteRepository implements QuoteRepository {
        private final Map<QuoteId, Quote> store = new HashMap<>();

        @Override
        public Quote save(Quote quote) {
            store.put(quote.id(), quote);
            return quote;
        }

        @Override
        public Optional<Quote> findById(QuoteId id) {
            return Optional.ofNullable(store.get(id));
        }
    }
}
