package com.rightmove.hexagonaldemo.web.controller;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.domain.port.in.CreateQuoteUseCase;
import com.rightmove.hexagonaldemo.domain.port.in.GetQuoteUseCase;
import com.rightmove.hexagonaldemo.web.dto.CreateQuoteRequestDto;
import com.rightmove.hexagonaldemo.web.dto.QuoteResponseDto;
import com.rightmove.hexagonaldemo.web.mapper.QuoteWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final CreateQuoteUseCase createQuoteUseCase;
    private final GetQuoteUseCase getQuoteUseCase;
    private final QuoteWebMapper mapper;

    public QuoteController(CreateQuoteUseCase createQuoteUseCase, GetQuoteUseCase getQuoteUseCase, QuoteWebMapper mapper) {
        this.createQuoteUseCase = createQuoteUseCase;
        this.getQuoteUseCase = getQuoteUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public QuoteResponseDto createQuote(@Valid @RequestBody CreateQuoteRequestDto request) {
        Quote quote = createQuoteUseCase.createQuote(request.customerName(), request.premium());
        return mapper.toResponseDto(quote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponseDto> getQuote(@PathVariable UUID id) {
        return getQuoteUseCase.getQuote(QuoteId.of(id))
                .map(mapper::toResponseDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
