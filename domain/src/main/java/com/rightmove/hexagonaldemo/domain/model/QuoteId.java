package com.rightmove.hexagonaldemo.domain.model;

import java.util.UUID;

public record QuoteId(UUID value) {

    public static QuoteId generate() {
        return new QuoteId(UUID.randomUUID());
    }

    public static QuoteId of(UUID value) {
        return new QuoteId(value);
    }
}
