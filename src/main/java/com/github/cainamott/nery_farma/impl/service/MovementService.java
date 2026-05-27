package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.BatchRequestDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.mappers.BatchMapper;
import com.github.cainamott.nery_farma.impl.mappers.MovementMapper;
import com.github.cainamott.nery_farma.impl.repository.BatchRepository;
import com.github.cainamott.nery_farma.impl.repository.MovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovementService {

    public final MovementRepository movementRepository;

    public final MedicationService medicationService;

    public final BatchService batchService;

    public static BatchMapper batchMapper;

    public static MovementMapper movementMapper;


    // metodo para logica de retirada/acrescimo de estoque

    //entrada de estoque
    public BatchDTO stockEntry(StockEntryDTO dto) {

        medicationService.validateMedication(dto.medicationId());
        entryValidation(dto);

            // acrescer a esse lote a quantidade de entrada

        stockOperationMethod(dto);

        // buildar o movement dto
        // mapear para entidade
        // salvar no banco
        // retornar o lote completo

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


    private void stockOperationMethod(StockEntryDTO dto) {

    }

    // validar lote

    // validar medicamento

    // mapear para entrada/saida

    //persist movement

}

