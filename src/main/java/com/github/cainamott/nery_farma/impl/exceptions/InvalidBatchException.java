package com.github.cainamott.nery_farma.impl.exceptions;

public class InvalidBatchException extends RuntimeException {

    public InvalidBatchException() {
        super("Lote mais recente é inválido!");
    }
}
