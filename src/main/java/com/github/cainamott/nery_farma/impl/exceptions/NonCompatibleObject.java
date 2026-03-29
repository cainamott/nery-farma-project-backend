package com.github.cainamott.nery_farma.impl.exceptions;

import org.springframework.stereotype.Component;

public class NonCompatibleObject extends RuntimeException {
    public NonCompatibleObject(String message) {
        super(message);
    }
}
