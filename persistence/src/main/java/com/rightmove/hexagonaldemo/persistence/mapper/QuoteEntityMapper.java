package com.rightmove.hexagonaldemo.persistence.mapper;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.persistence.entity.QuoteEntity;
import org.springframework.stereotype.Component;

@Component
public class QuoteEntityMapper {

    public QuoteEntity toEntity(Quote quote) {
        return new QuoteEntity(quote.id().value(), quote.customerName(), quote.premium());
    }

    public Quote toDomain(QuoteEntity entity) {
        return new Quote(QuoteId.of(entity.getId()), entity.getCustomerName(), entity.getPremium());
    }
}
