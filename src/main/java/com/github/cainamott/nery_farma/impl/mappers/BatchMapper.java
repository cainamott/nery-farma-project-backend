package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.models.Medication;
import org.springframework.stereotype.Component;

@Component
public class BatchMapper {

    public Batch mapToEntity(BatchRequestDTO batchRequestDTO, Medication medication) {

        if (batchRequestDTO == null) {
            return null;
        }

        return new Batch(
                null,                               // id gerado pelo banco
                medication,
                batchRequestDTO.batchNumber(),
                batchRequestDTO.fabricationDate(),
                batchRequestDTO.expirationDate(),
                batchRequestDTO.currentQuantity()
        );
    }

    public BatchDTO mapToDto(Batch batch) {

        if (batch == null) {
            return null;
        }

        Medication medication = batch.getMedication();

        return new BatchDTO(
                batch.getId(),
                batch.getBatchNumber(),
                batch.getFabricationDate(),
                batch.getExpirationDate(),
                batch.getCurrentQuantity(),
                medication != null ? medication.getId()             : null,
                medication != null ? medication.getMedicationName() : null,
                medication != null ? medication.getDosageForm()     : null,
                medication != null ? medication.getUnitary()        : null
        );
    }

    public StockEntryDTO mapDtoToStockEntryDto(BatchDTO batchDTO, StockEntryDTO entryDTO) {

        if (batchDTO == null) {
            return null;
        }

        return StockEntryDTO.builder()
                .medicationId(batchDTO.medicationId())
                .batchNumber(batchDTO.batchNumber())
                .fabricationDate(batchDTO.fabricationDate())
                .expirationDate(batchDTO.expirationDate())
                .existingBatchId(batchDTO.id())
                .quantity(batchDTO.currentQuantity())
                .reason(entryDTO.reason()) // razão não existe no BatchDTO, preencher conforme necessidade
                .build();
    }
}