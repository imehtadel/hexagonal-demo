package com.rightmove.hexagonaldemo.web.mapper;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.web.dto.QuoteResponseDto;
import org.springframework.stereotype.Component;

@Component
public class QuoteWebMapper {

    public QuoteResponseDto toResponseDto(Quote quote) {
        return new QuoteResponseDto(quote.id().value(), quote.customerName(), quote.premium());
    }
}
