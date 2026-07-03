package com.github.cainamott.nery_farma.impl.exceptions;

public class OrderRejectedException extends RuntimeException {

    public OrderRejectedException(String message) {
        super("Pedido Rejeitado! " + message);
    }
}
