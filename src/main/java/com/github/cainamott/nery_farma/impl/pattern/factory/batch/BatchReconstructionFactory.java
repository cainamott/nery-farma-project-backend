package com.github.cainamott.nery_farma.impl.pattern.factory.batch;

import com.github.cainamott.nery_farma.impl.models.Batch;
import org.springframework.stereotype.Component;

/**
 * Concrete Creator for reconstructing an existing batch with an updated quantity.
 */
@Component
public class BatchReconstructionFactory extends BatchFactory {

    @Override
    protected Batch buildBatch(BatchBuildContext context) {
        return Batch.builder()
                .id(context.id())
                .medication(context.medication())
                .batchNumber(context.batchNumber())
                .fabricationDate(context.fabricationDate())
                .expirationDate(context.expirationDate())
                .currentQuantity(context.currentQuantity())
                .build();
    }
}
