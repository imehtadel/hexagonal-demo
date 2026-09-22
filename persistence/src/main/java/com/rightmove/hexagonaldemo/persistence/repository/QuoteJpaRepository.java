package com.rightmove.hexagonaldemo.persistence.repository;

import com.rightmove.hexagonaldemo.persistence.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuoteJpaRepository extends JpaRepository<QuoteEntity, UUID> {
}
