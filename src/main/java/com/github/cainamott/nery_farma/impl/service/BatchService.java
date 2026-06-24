package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
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

        BatchDTO dto = mapper.mapToDto(repository.findByMedicationIdOrderByExpirationDateAsc(medicationId));

        if(dto == null) {

            return null;
        }

        if (validateBatch(dto.batchNumber(), dto.expirationDate(), dto.currentQuantity()) == false) {
            throw new RuntimeException("Lote mais recente é inválido!");
        }

        return dto;
    }

    public Batch createNewBatch(BatchRequestDTO batchDTO) {

        Medication medication = medicationService.findById(batchDTO.medicationId()).orElseThrow();

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

    public Boolean validateBatch(Integer batchNumber, LocalDate expirationDate, Integer quantity) {

        var batchFound = findByBatchNumber(batchNumber).get();

        if (batchFound.getCurrentQuantity() < 0 || findByBatchNumber(batchNumber).isEmpty() || expirationDate.isBefore(LocalDate.now())){
            return Boolean.FALSE;
        }
        else return Boolean.TRUE;
    }

    private Integer generateNewBatchNumber(UUID id) {

        var latestBatch = getBatchByExpirationDate(id);
        if(latestBatch == null){
            return 1;
        } else {
            return latestBatch.batchNumber() + 1;
        }
    }

    public Integer addQuantityToBatch(UUID medicationId, Integer quantity) {

        BatchDTO lote = getBatchByExpirationDate(medicationId);

        Medication medication = medicationService.findById(medicationId).orElseThrow();
        BatchBuildContext context = new BatchBuildContext(
                lote.id(),
                medication,
                lote.batchNumber(),
                lote.fabricationDate(),
                lote.expirationDate(),
                lote.currentQuantity() + quantity
        );

        repository.save(batchReconstructionFactory.createBatch(context));

        return quantity;
    }

    public void removeQuantityFromBatch(UUID medicationId, Integer quantity) {

        BatchDTO batch = getBatchByExpirationDate(medicationId);

        validateBatchMovement(batch, quantity);

        Medication medication = medicationService.findById(medicationId).orElseThrow();
        BatchBuildContext context = new BatchBuildContext(
                batch.id(),
                medication,
                batch.batchNumber(),
                batch.fabricationDate(),
                batch.expirationDate(),
                batch.currentQuantity() - quantity
        );

        repository.save(batchReconstructionFactory.createBatch(context));
    }

    public void validateBatchMovement(BatchDTO batch, Integer quantity) {

        if (batch.currentQuantity() < quantity) throw new
                RuntimeException("Não é possivel fazer o movimento de estoque");

    }

}
