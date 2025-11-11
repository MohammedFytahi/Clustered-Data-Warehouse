package com.progressoft.clustereddatawarehouse.service;

import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;

public interface DealValidationService {

    void validateDealNotExists(DealRequestDto dealRequestDto);

    void validateCurrencies(String fromCurrency, String toCurrency);
}