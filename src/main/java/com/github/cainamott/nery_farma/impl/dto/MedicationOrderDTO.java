package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.impl.models.Movement;
import com.github.cainamott.nery_farma.impl.models.User;

import java.util.Date;
import java.util.UUID;

public record MedicationOrderDTO(
        UUID id,
        UUID medicationId,
        Integer requestedQuantity,
        String reason,
        MedicationOrderStatus status,
        UUID requesterId,
        Movement movementId,
        Date createdAt
) {
}
