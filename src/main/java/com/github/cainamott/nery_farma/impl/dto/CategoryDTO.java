package com.github.cainamott.nery_farma.impl.dto;

import java.util.Date;
import java.util.UUID;

public record CategoryDTO(
        Integer id,
        String label,
        String description,
        Date creationDate
) {
}
