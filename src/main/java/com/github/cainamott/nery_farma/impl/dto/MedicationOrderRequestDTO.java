package com.github.cainamott.nery_farma.impl.dto;

import java.util.UUID;

public record MedicationOrderRequestDTO(
        UUID medicationId,
        Integer requestedQuantity,
        String reason
) {
}
