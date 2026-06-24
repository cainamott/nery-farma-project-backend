package com.github.cainamott.nery_farma.impl.pattern.factory.batch;

import com.github.cainamott.nery_farma.impl.models.Medication;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Value object carrying the data required by {@link BatchFactory} subclasses.
 */
public record BatchBuildContext(
        UUID id,
        Medication medication,
        Integer batchNumber,
        LocalDate fabricationDate,
        LocalDate expirationDate,
        Integer currentQuantity
) {
}
