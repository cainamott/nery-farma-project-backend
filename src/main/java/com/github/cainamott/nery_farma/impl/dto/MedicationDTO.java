package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.models.Category;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record MedicationDTO(
        UUID id,
        String medicationName,
        Category categoryId,
        String activePrinciple,
        String dosageForm,
        String unitary,
        Integer minimalStorage,
        LocalDate insertionDate,
        Boolean isActive
) {
}
