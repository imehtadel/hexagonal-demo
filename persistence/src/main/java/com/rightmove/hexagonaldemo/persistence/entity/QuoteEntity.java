package com.rightmove.hexagonaldemo.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "quote")
public class QuoteEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private BigDecimal premium;

    protected QuoteEntity() {
        // required by JPA
    }

    public QuoteEntity(UUID id, String customerName, BigDecimal premium) {
        this.id = id;
        this.customerName = customerName;
        this.premium = premium;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getPremium() {
        return premium;
    }
}
