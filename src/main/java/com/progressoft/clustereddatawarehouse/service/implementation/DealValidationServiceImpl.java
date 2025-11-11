package com.progressoft.clustereddatawarehouse.service.implementation;

import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.exception.DuplicateDealException;
import com.progressoft.clustereddatawarehouse.repository.DealRepository;
import com.progressoft.clustereddatawarehouse.service.DealValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealValidationServiceImpl implements DealValidationService {

    private final DealRepository dealRepository;

    @Override
    public void validateDealNotExists(DealRequestDto dealRequestDto) {
        if (dealRepository.existsById(dealRequestDto.id())) {
            throw new DuplicateDealException(dealRequestDto.id());
        }
    }

    @Override
    public void validateCurrencies(String fromCurrency, String toCurrency) {
        if (fromCurrency == null || toCurrency == null) {
            throw new IllegalArgumentException("Currencies cannot be null.");
        }
        if (fromCurrency.equals(toCurrency)) {
            throw new IllegalArgumentException("Source and target currencies cannot be the same.");
        }
    }
}