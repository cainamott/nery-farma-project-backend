package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.dto.StockDispatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.mappers.BatchMapper;
import com.github.cainamott.nery_farma.impl.mappers.MovementMapper;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.repository.MovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MovementService {

    public final MovementRepository movementRepository;
    public final MedicationService medicationService;
    public final BatchService batchService;
    public static BatchMapper batchMapper;
    public static MovementMapper movementMapper;

    //entrada de estoque
    public BatchDTO stockEntry(StockEntryDTO entryDTO) {

        medicationService.validateMedication(entryDTO.medicationId());
        var dto = entryValidation(entryDTO);
        if(dto != null) {
            entryDTO = batchMapper.mapDtoToStockEntryDto(dto);
        }
        stockEntryMethod(entryDTO);
        Batch updatedBatch = batchService.findByBatchNumber(entryDTO.batchNumber()).orElseThrow();
        movementRepository.save(movementMapper.mapEntryToEntity(entryDTO, updatedBatch));
        return batchMapper.mapToDto(updatedBatch);

    }

    private BatchDTO entryValidation(StockEntryDTO dto) {

        if ((batchService.findByBatchNumber(dto.batchNumber()).isEmpty())) {

            return batchMapper.mapToDto(batchService.createNewBatch(BatchRequestDTO
                    .builder()
                    .batchNumber(dto.batchNumber())
                    .medicationId(dto.medicationId())
                    .expirationDate(dto.expirationDate())
                    .fabricationDate(dto.fabricationDate())
                    .currentQuantity(dto.quantity())
                    .build()));
        }
        else return null;
    }


    private void stockEntryMethod(StockEntryDTO dto) {

        batchService.validateBatch(dto.batchNumber(), dto.expirationDate(), dto.quantity());
        batchService.addQuantityToBatch(dto.medicationId(), dto.quantity());

    }

    public BatchDTO stockDispatch(StockDispatchDTO dispatchDTO){

        // validar o lote/medicamento
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

    private void stockRemovalMethod(StockEntryDTO dto) {

        batchService.removeQuantityFromBatch(
                dto.medicationId(),
                dto.quantity());

    }

    // validar lote

    // validar medicamento

    // mapear para entrada/saida

    //persist movement

}

