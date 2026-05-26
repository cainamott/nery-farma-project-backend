package com.github.cainamott.nery_farma.impl.dto;

import java.util.Date;
import java.util.UUID;

public record BatchDTO(
         UUID id,
         Integer batchNumber,
         Date fabricationDate,
         Date expirationDate,
         Integer currentQuantity,
         UUID medicationId,
         String medicationName,
         String dosageForm,
         String unitary
) {
}