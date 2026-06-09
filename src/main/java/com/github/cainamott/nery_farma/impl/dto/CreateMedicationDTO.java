package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.models.Category;

import java.util.UUID;

public record CreateMedicationDTO(
        String medicationName,
        UUID categoryId,
        String activePrinciple,
        String dosageForm,
        String unitary,
        Integer minimalStorage,
        Boolean isActive
) {
}
