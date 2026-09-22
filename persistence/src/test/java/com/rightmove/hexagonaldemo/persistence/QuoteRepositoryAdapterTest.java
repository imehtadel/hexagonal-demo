package com.rightmove.hexagonaldemo.persistence;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.persistence.mapper.QuoteEntityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({QuoteRepositoryAdapter.class, QuoteEntityMapper.class})
class QuoteRepositoryAdapterTest {

    @Autowired
    private QuoteRepositoryAdapter quoteRepositoryAdapter;

    @Test
    void savesAndReloadsAQuote() {
        Quote quote = Quote.createNew("Jane Doe", BigDecimal.valueOf(199.99));

        quoteRepositoryAdapter.save(quote);

        assertThat(quoteRepositoryAdapter.findById(quote.id())).contains(quote);
    }

    @Test
    void returnsEmptyWhenQuoteDoesNotExist() {
        assertThat(quoteRepositoryAdapter.findById(QuoteId.generate())).isEmpty();
    }
}
