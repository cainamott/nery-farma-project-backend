package com.github.cainamott.nery_farma.impl.pattern.factory.batch;

import com.github.cainamott.nery_farma.impl.models.Batch;

/**
 * Abstract Creator (Factory Method pattern).
 * Subclasses implement {@link #buildBatch(BatchBuildContext)} to produce
 * {@link Batch} instances for different creation scenarios.
 */
public abstract class BatchFactory {

    public Batch createBatch(BatchBuildContext context) {
        return buildBatch(context);
    }

    protected abstract Batch buildBatch(BatchBuildContext context);
}
