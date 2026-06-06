package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.impl.models.Medication;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderReceivementRequest(

        UUID orderId,
        Medication medication,
        MedicationOrderStatus orderStatus
) {
}
