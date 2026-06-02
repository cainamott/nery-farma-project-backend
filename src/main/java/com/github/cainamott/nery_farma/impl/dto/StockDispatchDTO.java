package com.github.cainamott.nery_farma.impl.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record StockDispatchDTO(
        UUID batchId,
        Integer batchNumber,
        UUID medicationId,
        Integer quantity,
        String reason
) {
}
