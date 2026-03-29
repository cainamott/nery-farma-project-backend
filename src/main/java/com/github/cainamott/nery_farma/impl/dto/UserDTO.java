package com.github.cainamott.nery_farma.impl.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String hashPassword,
        String email,
        Integer registryNumber,
        String profile,
        Boolean active
) {
}
