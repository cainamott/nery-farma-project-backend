package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.mappers.BatchMapper;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.repository.BatchRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository repository;

    private final MedicationService medicationService;

    private static BatchMapper mapper;

    public BatchDTO getBatchByExpirationDate(UUID medicationId) {

        BatchDTO dto = mapper.mapToDto(repository.
                findByMedicationIdOrderByExpirationDateAsc(medicationId));

        if (validateBatch(dto.batchNumber(), dto.expirationDate(), dto.currentQuantity()) == false) {
            throw new RuntimeException("Lote mais recente é inválido!");
        }

        return dto;
    }

    public Batch createNewBatch(BatchRequestDTO batchDTO) {

        Medication medication = medicationService.findById(batchDTO.medicationId()).orElseThrow();

        return repository.save(

                Batch.builder()
                        .id(UUID.randomUUID())
                        .medication(medication)
                        .batchNumber(generateNewBatchNumber(medication.getId()))
                        .fabricationDate(batchDTO.fabricationDate())
                        .expirationDate(batchDTO.expirationDate())
                        .currentQuantity(batchDTO.currentQuantity()).build()
        );

    }

    public Optional<Batch> findByBatchNumber(Integer batchNumber) {

        return repository.findByBatchNumber(batchNumber);
    }

    public Boolean validateBatch(Integer batchNumber, Date expirationDate, Integer quantity) {

        if (quantity < 0 || findByBatchNumber(batchNumber).isEmpty() || expirationDate.before(Date.from(Instant.now()))){
            return Boolean.FALSE;
        }
        else return Boolean.TRUE;
    }

    private Integer generateNewBatchNumber(UUID id) {

        var latestBatch = getBatchByExpirationDate(id);
        return latestBatch.batchNumber() + 1;
    }

    public Integer addQuantityToBatch(UUID medicationId, Integer quantity) {

        BatchDTO lote = getBatchByExpirationDate(medicationId);

        repository.save(Batch
                .builder()
                .id(lote.id())
                .medication(medicationService.findById(medicationId).orElseThrow())
                .batchNumber(lote.batchNumber())
                .fabricationDate(lote.fabricationDate())
                .expirationDate(lote.expirationDate())
                .currentQuantity(lote.currentQuantity() + quantity)
                .build());

        return quantity;
    }

    public void removeQuantityFromBatch(UUID medicationId, Integer quantity) {

        BatchDTO batch = getBatchByExpirationDate(medicationId);

        validateBatchMovement(batch, quantity);

        repository.save(Batch
                .builder()
                .id(batch.id())
                .medication(medicationService.findById(medicationId).orElseThrow())
                .batchNumber(batch.batchNumber())
                .fabricationDate(batch.fabricationDate())
                .expirationDate(batch.expirationDate())
                .currentQuantity(batch.currentQuantity() - quantity)
                .build());
    }

    public void validateBatchMovement(BatchDTO batch, Integer quantity) {

        if (batch.currentQuantity() < quantity) throw new
                RuntimeException("Não é possivel fazer o movimento de estoque");

    }

}
