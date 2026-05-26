package com.github.cainamott.nery_farma.impl.dto;

import java.util.Date;
import java.util.UUID;

public record StockReceptionDTO(
        UUID medicationId,
        Integer batchNumber,
        Date fabricationDate,
        Date expirationDate,
        UUID existingBatchId,
        Integer quantity,
        String reason
) {
}
