package com.github.cainamott.nery_farma.impl.dto;

import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record StockEntryDTO(
        UUID medicationId,
        Integer batchNumber,
        Date fabricationDate,
        Date expirationDate,
        UUID existingBatchId,
        Integer quantity,
        String reason
) {
}
