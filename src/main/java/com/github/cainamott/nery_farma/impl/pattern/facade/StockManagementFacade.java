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
 * Facade over the stock-management subsystem (medication validation, batch lifecycle,
 * movement persistence). Provides a simplified entry point for stock entry and dispatch
 * operations that would otherwise require coordinating multiple services and mappers.
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
        var dto = entryValidation(entryDTO);
        if (dto != null) {
            entryDTO = batchMapper.mapDtoToStockEntryDto(dto, entryDTO);
        }
        stockEntryMethod(entryDTO);
        Batch updatedBatch = batchService.findByBatchNumber(entryDTO.batchNumber()).orElseThrow();
        movementRepository.save(movementMapper.mapEntryToEntity(entryDTO, updatedBatch));
        return batchMapper.mapToDto(updatedBatch);
    }

    public BatchDTO registerStockDispatch(StockDispatchDTO dispatchDTO) {

        medicationService.validateMedication(dispatchDTO.medicationId());
        var batchFound = batchService.findByBatchNumber(dispatchDTO.batchNumber()).get();
        batchService.validateBatch(
                dispatchDTO.batchNumber(),
                batchFound.getExpirationDate(),
                batchFound.getCurrentQuantity());

        return null;

        // mapear para saida
        // gerar movimento de saida e decrementar estoque
    }

    private BatchDTO entryValidation(StockEntryDTO dto) {

        if ((batchService.findByBatchNumber(dto.batchNumber()).isEmpty())) {

            var newBatch = batchService.createNewBatch(BatchRequestDTO
                    .builder()
                    .batchNumber(dto.batchNumber())
                    .medicationId(dto.medicationId())
                    .expirationDate(dto.expirationDate())
                    .fabricationDate(dto.fabricationDate())
                    .currentQuantity(dto.quantity())
                    .build());

            return batchMapper.mapToDto(newBatch);
        } else return null;
    }

    private void stockEntryMethod(StockEntryDTO dto) {

        batchService.validateBatch(dto.batchNumber(), dto.expirationDate(), dto.quantity());
        batchService.addQuantityToBatch(dto.medicationId(), dto.quantity());
    }

    private void stockRemovalMethod(StockEntryDTO dto) {

        batchService.removeQuantityFromBatch(
                dto.medicationId(),
                dto.quantity());
    }
}
