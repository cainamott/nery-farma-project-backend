package com.github.cainamott.nery_farma.impl.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record BatchRequestDTO(
        UUID medicationId,
        Integer batchNumber,
        LocalDate fabricationDate,
        LocalDate expirationDate,
        Integer currentQuantity
) {
}
