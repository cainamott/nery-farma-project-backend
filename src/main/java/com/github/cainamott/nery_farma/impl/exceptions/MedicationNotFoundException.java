package com.github.cainamott.nery_farma.impl.exceptions;

import java.util.UUID;

public class MedicationNotFoundException extends RuntimeException {

    public MedicationNotFoundException() {
        super("Medicamento não encontrado na base de dados!");
    }

    public MedicationNotFoundException(UUID id) {
        super("Medicamento não encontrado para essa Id");
    }
}
