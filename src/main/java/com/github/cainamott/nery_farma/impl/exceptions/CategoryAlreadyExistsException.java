package com.github.cainamott.nery_farma.impl.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException() {
        super("Nome de categoria já existente");
    }
}
