package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.auth.models.User;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdateOrderRequest(

        UUID orderId,
        UUID medicationId,
        Integer requestedQuantity,
        MedicationOrderStatus orderStatus,
        User requester

) {
}
