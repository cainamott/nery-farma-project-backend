package com.github.cainamott.nery_farma.impl.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Nome de categoria não encontrado");
    }
}
