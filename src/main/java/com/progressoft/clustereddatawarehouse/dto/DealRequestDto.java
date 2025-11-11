package com.progressoft.clustereddatawarehouse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DealRequestDto(
        @NotBlank(message = "Deal ID cannot be blank")
        String id,

        @NotBlank(message = "From currency code is required")
        String fromCurrencyIsoCode,

        @NotBlank(message = "To currency code is required")
        String toCurrencyIsoCode,

        @NotNull(message = "Deal timestamp cannot be null")
        LocalDateTime dealTimestamp,

        @NotNull(message = "Deal amount cannot be null")
        @Positive(message = "Deal amount must be positive")
        BigDecimal dealAmount
) {}
