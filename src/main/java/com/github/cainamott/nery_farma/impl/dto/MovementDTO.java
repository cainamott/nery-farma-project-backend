package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MovementType;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record MovementDTO(

         UUID id,
         MovementType type,
         Integer quantity,
         String reason,
         LocalDate createdAt,
         UUID batchId,
         Integer batchNumber,
         LocalDate expirationDate,
         UUID medicationId,
         String medicationName

) {
}
