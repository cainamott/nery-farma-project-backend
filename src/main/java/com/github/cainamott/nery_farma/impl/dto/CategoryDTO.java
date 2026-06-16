package com.github.cainamott.nery_farma.impl.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String label,
        String description,
        LocalDate creationDate
) {
}
