package com.github.cainamott.nery_farma.impl.dto;

import java.util.UUID;

public record StockDispatchDTO(
        UUID loteId,
        Integer quantity,
        String reason
) {
}
