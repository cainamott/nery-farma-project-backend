package com.github.cainamott.nery_farma.impl.exceptions;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException() {
        super("Não é possivel fazer o movimento de estoque");
    }
}
