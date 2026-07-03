package com.github.cainamott.nery_farma.impl.exceptions;

public class OrderStatusTransitionException extends RuntimeException {

    public OrderStatusTransitionException(String message) {
        super(message);
    }
}
