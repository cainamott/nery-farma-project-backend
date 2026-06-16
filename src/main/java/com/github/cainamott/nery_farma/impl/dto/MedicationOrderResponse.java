package com.github.cainamott.nery_farma.impl.dto;

import java.time.LocalDate;
import java.util.Date;

public record MedicationOrderResponse(
        String status,
        LocalDate estimatedDeliveryDate,
        String message
) {
}
