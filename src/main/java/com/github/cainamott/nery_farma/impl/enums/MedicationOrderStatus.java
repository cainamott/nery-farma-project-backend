package com.github.cainamott.nery_farma.impl.enums;

import com.github.cainamott.nery_farma.impl.exceptions.NonCompatibleObject;

public enum MedicationOrderStatus {
    PENDING,
    APPROVED,
    DONE,
    CANCELLED;

    public static MedicationOrderStatus fromApi(String value){
        if(value == null || value.isBlank()) {
            return null;
        }
        else {
            return switch (value){
                case ("pending") -> PENDING;
                case ("done") -> DONE;
                case ("approved") -> APPROVED;
                case ("cancelled") -> CANCELLED;
                default -> throw new NonCompatibleObject("Status de pedido inválido");
            };
        }
    }

    public String toApi(){
        return switch (this){
            case PENDING -> "pending";
            case DONE -> "done";
            case APPROVED -> "approved";
            case CANCELLED -> "cancelled";
        };
    }
}
