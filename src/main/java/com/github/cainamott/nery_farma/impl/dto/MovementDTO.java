package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MovementType;

import java.util.Date;
import java.util.UUID;

public record MovementDTO(

         UUID id,
         MovementType type,
         Integer quantity,
         String reason,
         Date createdAt,
         UUID batchId,
         Integer batchNumber,
         Date expirationDate,
         UUID medicationId,
         String medicationName,
         UUID userId,
         String userName
) {
}
