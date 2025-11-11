package com.progressoft.clustereddatawarehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fx_deals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    @Id
    @Column( nullable = false, updatable = false)
    @NotBlank(message = "Deal ID cannot be blank")
    private String id;

    @Column(name = "from_currency", nullable = false, length = 3)
    private String fromCurrencyIsoCode;

    @Column(name = "to_currency", nullable = false, length = 3)
    private String toCurrencyIsoCode;

    @Column(name = "deal_timestamp", nullable = false)
    @NotNull(message = "Deal timestamp cannot be null")
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount", nullable = false, precision = 19, scale = 4)
    @NotNull(message = "Deal amount cannot be null")
    @Positive(message = "Deal amount must be positive")
    private BigDecimal dealAmount;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
