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
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    /**
     * Create a new deal.
     *
     * @param dealRequest the deal details to be created
     * @return the created deal with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@RequestBody @Valid DealRequestDto dealRequest) {
        DealResponseDto response = dealService.createDeal(dealRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieve a deal by its unique identifier.
     *
     * @param id the unique ID of the deal
     * @return the corresponding deal if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<DealResponseDto> getDealById(@PathVariable String id) {
        DealResponseDto response = dealService.getDealById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve all deals.
     *
     * @return a list of all deals
     */
    @GetMapping
    public ResponseEntity<List<DealResponseDto>> getAllDeals() {
        List<DealResponseDto> response = dealService.getAllDeals();
        return ResponseEntity.ok(response);
    }
}
