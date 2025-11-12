package com.progressoft.clustereddatawarehouse.service.implementation;

import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.dto.DealResponseDto;
import com.progressoft.clustereddatawarehouse.exception.DealNotFoundException;
import com.progressoft.clustereddatawarehouse.mapper.DealMapper;
import com.progressoft.clustereddatawarehouse.model.Deal;
import com.progressoft.clustereddatawarehouse.repository.DealRepository;
import com.progressoft.clustereddatawarehouse.service.DealService;
import com.progressoft.clustereddatawarehouse.service.DealValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final DealMapper dealMapper;
    private final DealValidationService validationService;

    @Override
    public DealResponseDto createDeal(DealRequestDto dealRequest) {
        log.info("Creating deal with ID: {}", dealRequest.id());

        validationService.validateDealNotExists(dealRequest);
        validationService.validateCurrencies(
                dealRequest.fromCurrencyIsoCode(),
                dealRequest.toCurrencyIsoCode()
        );

        Deal deal = dealMapper.toEntity(dealRequest);
        Deal savedDeal = dealRepository.save(deal);

        log.info("Successfully created deal with ID: {}", savedDeal.getId());
        return dealMapper.toDto(savedDeal);
    }

}