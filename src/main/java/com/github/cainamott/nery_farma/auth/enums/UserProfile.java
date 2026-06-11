package com.github.cainamott.nery_farma.auth.enums;

import com.github.cainamott.nery_farma.impl.exceptions.NonCompatibleObject;
import org.springframework.security.core.GrantedAuthority;

public enum UserProfile implements GrantedAuthority {

    GERENTE,
    AUXILIAR,
    PROFISSIONAL_SAUDE;


    public static UserProfile fromApi(String value){
        if(value == null || value.isBlank()) {
            return null;
        }
        else {
            return switch (value){
                case ("gerente") -> GERENTE;
                case ("auxiliar") -> AUXILIAR;
                case ("profissional_saúde") -> PROFISSIONAL_SAUDE;
                default -> throw new NonCompatibleObject("Perfil inválido");
            };
        }
    }

    public String toApi(){
        return switch (this){
                case GERENTE -> "gerente";
                case AUXILIAR -> "auxiliar";
                case PROFISSIONAL_SAUDE -> "profissional_saude";
        };
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
