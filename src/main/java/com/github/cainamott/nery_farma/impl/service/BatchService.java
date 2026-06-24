package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.exceptions.InsufficientStockException;
import com.github.cainamott.nery_farma.impl.exceptions.InvalidBatchException;
import com.github.cainamott.nery_farma.impl.mappers.BatchMapper;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.pattern.factory.batch.BatchBuildContext;
import com.github.cainamott.nery_farma.impl.pattern.factory.batch.BatchReconstructionFactory;
import com.github.cainamott.nery_farma.impl.pattern.factory.batch.NewBatchFactory;
import com.github.cainamott.nery_farma.impl.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository repository;
    private final MedicationService medicationService;
    private final BatchMapper mapper;
    private final NewBatchFactory newBatchFactory;
    private final BatchReconstructionFactory batchReconstructionFactory;

    public BatchDTO getBatchByExpirationDate(UUID medicationId) {
        Batch batch = repository.findByMedicationIdOrderByExpirationDateAsc(medicationId);
        BatchDTO dto = mapper.mapToDto(batch);

        if (dto == null) {
            return null;
        }

        if (!validateBatch(dto.batchNumber(), dto.expirationDate(), dto.currentQuantity())) {
            throw new InvalidBatchException();
        }

        return dto;
    }

    public Batch createNewBatch(BatchRequestDTO batchDTO) {
        Medication medication = requireMedication(batchDTO.medicationId());

        BatchBuildContext context = new BatchBuildContext(
                null,
                medication,
                generateNewBatchNumber(medication.getId()),
                batchDTO.fabricationDate(),
                batchDTO.expirationDate(),
                batchDTO.currentQuantity()
        );

        return repository.save(newBatchFactory.createBatch(context));
    }

    public Optional<Batch> findByBatchNumber(Integer batchNumber) {
        return repository.findByBatchNumber(batchNumber);
    }

    public Optional<Batch> findById(UUID batchId) {
        return repository.findById(batchId);
    }

    public boolean validateBatch(Integer batchNumber, LocalDate expirationDate, Integer quantity) {
        Optional<Batch> batchOptional = findByBatchNumber(batchNumber);

        if (batchOptional.isEmpty()) {
            return false;
        }

        Batch batch = batchOptional.get();
        return batch.getCurrentQuantity() >= 0
                && !expirationDate.isBefore(LocalDate.now());
    }

    public Integer addQuantityToBatch(UUID medicationId, Integer quantity) {
        BatchDTO batch = getBatchByExpirationDate(medicationId);
        saveReconstructedBatch(batch, medicationId, batch.currentQuantity() + quantity);
        return quantity;
    }

    public void removeQuantityFromBatch(UUID medicationId, Integer quantity) {
        BatchDTO batch = getBatchByExpirationDate(medicationId);
        validateBatchMovement(batch, quantity);
        saveReconstructedBatch(batch, medicationId, batch.currentQuantity() - quantity);
    }

    public void validateBatchMovement(BatchDTO batch, Integer quantity) {
        if (batch.currentQuantity() < quantity) {
            throw new InsufficientStockException();
        }
    }

    private void saveReconstructedBatch(BatchDTO batch, UUID medicationId, int newQuantity) {
        Medication medication = requireMedication(medicationId);

        BatchBuildContext context = new BatchBuildContext(
                batch.id(),
                medication,
                batch.batchNumber(),
                batch.fabricationDate(),
                batch.expirationDate(),
                newQuantity
        );

        repository.save(batchReconstructionFactory.createBatch(context));
    }

    private Medication requireMedication(UUID medicationId) {
        return medicationService.findById(medicationId).orElseThrow();
    }

    private Integer generateNewBatchNumber(UUID medicationId) {
        BatchDTO latestBatch = getBatchByExpirationDate(medicationId);
        return latestBatch == null ? 1 : latestBatch.batchNumber() + 1;
    }
}
