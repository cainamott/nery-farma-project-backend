package com.github.cainamott.nery_farma.impl.repository;

import com.github.cainamott.nery_farma.impl.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BatchRepository extends JpaRepository<Batch, UUID> {

    Batch findByMedicationIdOrderByExpirationDateAsc(UUID medicationId);

    Optional<Batch> findByBatchNumber(Integer batchNumber);
}
