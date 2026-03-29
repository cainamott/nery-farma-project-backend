package com.github.cainamott.nery_farma.impl.dto;

public record CreateUserDTO(
        String name,
        String hashPassword,
        String email,
        Integer registryNumber,
        String profile
) {
}
