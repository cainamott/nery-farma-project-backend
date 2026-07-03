package com.github.cainamott.nery_farma.impl.pattern.factory.movement;

import com.github.cainamott.nery_farma.impl.enums.MovementType;
import org.springframework.stereotype.Component;

/**
 * Concrete Creator for stock entry movements.
 */
@Component
public class EntryMovementFactory extends MovementFactory {

    @Override
    protected MovementType getMovementType() {
        return MovementType.ENTRY;
    }
}
