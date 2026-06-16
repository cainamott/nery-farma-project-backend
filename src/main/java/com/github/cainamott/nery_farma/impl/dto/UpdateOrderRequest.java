package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.auth.models.User;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
public record UpdateOrderRequest(

        UUID orderId,
        UUID medicationId,
        Integer requestedQuantity,
        String orderStatus,
        Integer healthUnityId

) {
}
