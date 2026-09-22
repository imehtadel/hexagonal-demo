package com.rightmove.hexagonaldemo.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record QuoteResponseDto(UUID id, String customerName, BigDecimal premium) {
}
