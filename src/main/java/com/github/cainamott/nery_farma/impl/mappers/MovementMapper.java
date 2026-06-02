package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.MovementDTO;
import com.github.cainamott.nery_farma.impl.dto.StockDispatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.enums.MovementType;
import com.github.cainamott.nery_farma.impl.models.Batch;
import com.github.cainamott.nery_farma.impl.models.Movement;
import com.github.cainamott.nery_farma.impl.models.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MovementMapper {

    public Movement mapEntryToEntity(StockEntryDTO stockEntryDTO, Batch batch) {

        if (stockEntryDTO == null) {
            return null;
        }

        return new Movement(
                null,                       // id gerado pelo banco
                batch,
                MovementType.ENTRY,
                stockEntryDTO.quantity(),
                null,
                new Date()                  // criado_em preenchido no momento do registro
        );
    }

    public Movement mapDispatchToEntity(StockDispatchDTO stockDispatchDTO, Batch batch) {

        if (stockDispatchDTO == null) {
            return null;
        }

        return new Movement(
                null,
                batch,
                MovementType.DISPATCH,
                stockDispatchDTO.quantity(),
                null,
                new Date()
        );
    }

    public MovementDTO mapToDto(Movement movement) {

        if (movement == null) {
            return null;
        }

        Batch batch       = movement.getBatch();
        //User  user        = movement.getUser();

        return new MovementDTO(
                movement.getId(),
                movement.getType(),
                movement.getQuantity(),
                movement.getReason(),
                movement.getCreatedAt(),

                // dados resumidos do lote
                batch != null ? batch.getId()             : null,
                batch != null ? batch.getBatchNumber()     : null,
                batch != null ? batch.getExpirationDate() : null,

                // dados resumidos do medicamento via lote
                batch != null && batch.getMedication() != null ? batch.getMedication().getId()             : null,
                batch != null && batch.getMedication() != null ? batch.getMedication().getMedicationName() : null

                // dados resumidos do usuário
               /* user != null ? user.getId()   : null,
                user != null ? user.getName() : null*/
        );



    }
}