package com.rightmove.hexagonaldemo.persistence;

import com.rightmove.hexagonaldemo.domain.model.Quote;
import com.rightmove.hexagonaldemo.domain.model.QuoteId;
import com.rightmove.hexagonaldemo.domain.port.out.QuoteRepository;
import com.rightmove.hexagonaldemo.persistence.mapper.QuoteEntityMapper;
import com.rightmove.hexagonaldemo.persistence.repository.QuoteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuoteRepositoryAdapter implements QuoteRepository {

    private final QuoteJpaRepository jpaRepository;
    private final QuoteEntityMapper mapper;

    public QuoteRepositoryAdapter(QuoteJpaRepository jpaRepository, QuoteEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Quote save(Quote quote) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(quote)));
    }

    @Override
    public Optional<Quote> findById(QuoteId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
}
