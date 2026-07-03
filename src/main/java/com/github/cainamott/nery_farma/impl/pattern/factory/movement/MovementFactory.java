package com.github.cainamott.nery_farma.impl.pattern.factory.movement;

import com.github.cainamott.nery_farma.impl.enums.MovementType;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.models.Movement;

import java.time.LocalDate;

/**
 * Abstract Creator (Factory Method pattern).
 * Subclasses define the {@link MovementType} via {@link #getMovementType()}
 * while the shared construction logic lives in {@link #createMovement(Batch, Integer)}.
 */
public abstract class MovementFactory {

    protected abstract MovementType getMovementType();

    public Movement createMovement(Batch batch, Integer quantity) {

        if (batch == null) {
            return null;
        }

        return Movement.builder()
                .batch(batch)
                .type(getMovementType())
                .quantity(quantity)
                .createdAt(LocalDate.now())
                .build();
    }
}
