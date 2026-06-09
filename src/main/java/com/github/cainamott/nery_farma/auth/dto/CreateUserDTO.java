package com.github.cainamott.nery_farma.auth.dto;

public record CreateUserDTO(
        String name,
        String hashPassword,
        String email,
        Integer registryNumber,
        String profile
) {
}
