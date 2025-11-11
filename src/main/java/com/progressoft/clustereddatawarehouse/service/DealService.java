package com.progressoft.clustereddatawarehouse.service;

import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.dto.DealResponseDto;

import java.util.List;

public interface DealService {
    DealResponseDto createDeal(DealRequestDto dealRequest);
    DealResponseDto getDealById(String dealId);
    List<DealResponseDto> getAllDeals();
}
