package com.github.cainamott.nery_farma.impl.pattern.factory.batch;

import com.github.cainamott.nery_farma.impl.models.Batch;
import org.springframework.stereotype.Component;

/**
 * Concrete Creator for brand-new batches (no existing id).
 */
@Component
public class NewBatchFactory extends BatchFactory {

    @Override
    protected Batch buildBatch(BatchBuildContext context) {
        return Batch.builder()
                .medication(context.medication())
                .batchNumber(context.batchNumber())
                .fabricationDate(context.fabricationDate())
                .expirationDate(context.expirationDate())
                .currentQuantity(context.currentQuantity())
                .build();
    }
}
