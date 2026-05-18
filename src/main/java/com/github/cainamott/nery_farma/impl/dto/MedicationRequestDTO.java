package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.models.Category;

public record MedicationRequestDTO(
        String medicationName,
        Category categoryId,
        String activePrinciple,
        String dosageForm,
        String unitary,
        Integer minimalStorage,
        Boolean isActive
) {
}
