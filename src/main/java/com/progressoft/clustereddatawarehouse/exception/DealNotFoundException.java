package com.progressoft.clustereddatawarehouse.exception;

import lombok.Getter;

@Getter
public class DealNotFoundException extends RuntimeException {
    private final String dealId;

    public DealNotFoundException(String dealId) {
        super(String.format("Deal with ID '%s' not found", dealId));
        this.dealId = dealId;
    }
}

