package com.github.cainamott.nery_farma.impl.exceptions;

public class MedicationAlreadyExistsException extends RuntimeException {

    public MedicationAlreadyExistsException() {
        super("Medicamento com esse nome já existe");
    }
}
