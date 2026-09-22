package com.rightmove.hexagonaldemo.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateQuoteRequestDto(

        @NotBlank
        String customerName,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal premium) {
}
