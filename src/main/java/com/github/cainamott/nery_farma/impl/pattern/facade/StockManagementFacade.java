package com.github.cainamott.nery_farma.impl.pattern.facade;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.dto.StockDispatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.mappers.BatchMapper;
import com.github.cainamott.nery_farma.impl.mappers.MovementMapper;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.repository.MovementRepository;
import com.github.cainamott.nery_farma.impl.service.BatchService;
import com.github.cainamott.nery_farma.impl.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Facade do subsistema de estoque.
 * Coordena validação de medicamento, ciclo de vida do lote e persistência de movimentos.
 */
@Component
@AllArgsConstructor
public class StockManagementFacade {

    private final MovementRepository movementRepository;
    private final MedicationService medicationService;
    private final BatchService batchService;
    private final BatchMapper batchMapper;
    private final MovementMapper movementMapper;

    public BatchDTO registerStockEntry(StockEntryDTO entryDTO) {
        medicationService.validateMedication(entryDTO.medicationId());

        StockEntryDTO resolvedEntry = resolveEntryForExistingOrNewBatch(entryDTO);
        applyStockEntry(resolvedEntry);

        Batch updatedBatch = batchService.findByBatchNumber(resolvedEntry.batchNumber()).orElseThrow();
        movementRepository.save(movementMapper.mapEntryToEntity(resolvedEntry, updatedBatch));

        return batchMapper.mapToDto(updatedBatch);
    }

    public BatchDTO registerStockDispatch(StockDispatchDTO dispatchDTO) {
        medicationService.validateMedication(dispatchDTO.medicationId());

        Batch batch = batchService.findByBatchNumber(dispatchDTO.batchNumber()).orElseThrow();
        batchService.validateBatch(
                dispatchDTO.batchNumber(),
                batch.getExpirationDate(),
                batch.getCurrentQuantity());

        return null;
    }


     /* Se o lote ainda não existe, cria um novo e devolve o DTO de entrada atualizado.
     * Se já existe, devolve o DTO original sem alterações. */

    private StockEntryDTO resolveEntryForExistingOrNewBatch(StockEntryDTO entryDTO) {
        if (batchService.findByBatchNumber(entryDTO.batchNumber()).isPresent()) {
            return entryDTO;
        }

        Batch newBatch = batchService.createNewBatch(toBatchRequest(entryDTO));
        BatchDTO newBatchDto = batchMapper.mapToDto(newBatch);

        return batchMapper.mapDtoToStockEntryDto(newBatchDto, entryDTO);
    }

    private void applyStockEntry(StockEntryDTO entryDTO) {
        batchService.validateBatch(entryDTO.batchNumber(), entryDTO.expirationDate(), entryDTO.quantity());
        batchService.addQuantityToBatch(entryDTO.medicationId(), entryDTO.quantity());
    }

    private BatchRequestDTO toBatchRequest(StockEntryDTO entryDTO) {
        return BatchRequestDTO.builder()
                .batchNumber(entryDTO.batchNumber())
                .medicationId(entryDTO.medicationId())
                .expirationDate(entryDTO.expirationDate())
                .fabricationDate(entryDTO.fabricationDate())
                .currentQuantity(entryDTO.quantity())
                .build();
    }
}
