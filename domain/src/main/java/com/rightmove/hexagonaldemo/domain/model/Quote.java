package com.rightmove.hexagonaldemo.domain.model;

import java.math.BigDecimal;

public record Quote(QuoteId id, String customerName, BigDecimal premium) {

    public Quote {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("customerName must not be blank");
        }
        if (premium == null || premium.signum() < 0) {
            throw new IllegalArgumentException("premium must not be negative");
        }
    }

    public static Quote createNew(String customerName, BigDecimal premium) {
        return new Quote(QuoteId.generate(), customerName, premium);
    }
}
