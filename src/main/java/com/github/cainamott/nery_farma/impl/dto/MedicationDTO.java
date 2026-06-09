package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.models.Category;

import java.util.Date;
import java.util.UUID;

public record MedicationDTO(
        UUID id,
        String medicationName,
        UUID categoryId,
        String activePrinciple,
        String dosageForm,
        String unitary,
        Integer minimalStorage,
        Date insertionDate,
        Boolean isActive
) {
}
