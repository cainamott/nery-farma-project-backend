package com.github.cainamott.nery_farma.impl.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record UpdateOrderResponse(

        UUID orderId,
        LocalDate newEstimatedDate,
        String status,
        String message
) {
}
