package com.github.cainamott.nery_farma.impl.dto;

import java.util.UUID;

public record MedicationOrderRequest(
        UUID medicationId,
        Integer requestedQuantity,
        String message,
        Integer healthUnityId
) {
}
