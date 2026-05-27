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
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository repository;

    private final MedicationService medicationService;

    private static BatchMapper mapper;

    public BatchDTO getLotesOrdenadosPorValidade(UUID medicationId) {

        BatchDTO dto = mapper.mapToDto(repository.
                findByMedicationIdOrderByExpirationDateAsc(medicationId));

        if (validateBatch(dto.batchNumber(), dto.expirationDate(), dto.currentQuantity()) == false) {
            throw new RuntimeException("Lote mais recente é inválido!");
        }

        return dto;
    }

    public Batch createNewBatch(BatchRequestDTO batchDTO) {

        Medication medication = medicationService.findById(batchDTO.medicationId()).orElseThrow();

        return repository.save(Batch.builder()
                .id(UUID.randomUUID())
                .medication(medication)
                .batchNumber(batchDTO.batchNumber())
                .fabricationDate(batchDTO.fabricationDate())
                .expirationDate(batchDTO.expirationDate())
                .currentQuantity(batchDTO.currentQuantity()).build());

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
}
