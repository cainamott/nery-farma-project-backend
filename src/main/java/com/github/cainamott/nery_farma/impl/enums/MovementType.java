package com.github.cainamott.nery_farma.impl.enums;

import com.github.cainamott.nery_farma.impl.exceptions.NonCompatibleObject;

public enum MovementType {

    SAIDA,
    ENTRADA,
    AJUSTE;

    public static MovementType fromApi(String value){
        if(value == null || value.isBlank()) {
            return null;
        }
        else {
            return switch (value){
                case ("saida") -> SAIDA;
                case ("entrada") -> ENTRADA;
                case ("ajuste") -> AJUSTE;
                default -> throw new NonCompatibleObject("Perfil inválido");
            };
        }
    }

    public String toApi(){
        return switch (this){
            case SAIDA -> "saida";
            case ENTRADA -> "entrada";
            case AJUSTE -> "ajuste";
        };
    }

}
