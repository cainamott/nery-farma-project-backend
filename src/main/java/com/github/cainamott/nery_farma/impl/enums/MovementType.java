package com.github.cainamott.nery_farma.impl.enums;

import com.github.cainamott.nery_farma.impl.exceptions.NonCompatibleObject;

public enum MovementType {

    DISPATCH,
    ENTRY,
    ADJUSTMENT;

    public static MovementType fromApi(String value){
        if(value == null || value.isBlank()) {
            return null;
        }
        else {
            return switch (value){
                case ("dispatch") -> DISPATCH;
                case ("entry") -> ENTRY;
                case ("adjustment") -> ADJUSTMENT;
                default -> throw new NonCompatibleObject("Perfil inválido");
            };
        }
    }

    public String toApi(){
        return switch (this){
            case DISPATCH -> "dispatch";
            case ENTRY -> "entry";
            case ADJUSTMENT -> "adjustment";
        };
    }

}
