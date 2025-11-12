package com.progressoft.clustereddatawarehouse.controller;


import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.dto.DealResponseDto;
import com.progressoft.clustereddatawarehouse.service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing foreign exchange deals.
 * Provides endpoints to create and retrieve deals.
 */
@RestController
@RequestMapping("/api/v1/deals")
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@RequestBody @Valid DealRequestDto dealRequest) {
        DealResponseDto response = dealService.createDeal(dealRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
