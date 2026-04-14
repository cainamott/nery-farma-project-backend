package com.github.cainamott.nery_farma.impl.dto;

import com.github.cainamott.nery_farma.impl.enums.UserProfile;

import java.util.Date;

public record AuthResponseDTO(
        String accessToken,
        String type,
        Date expiresIn,
        String email,
        UserProfile profile
) {
}
