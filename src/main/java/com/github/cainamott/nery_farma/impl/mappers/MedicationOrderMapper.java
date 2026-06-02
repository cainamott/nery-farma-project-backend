package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.MedicationOrderDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationOrderRequestDTO;
import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.models.MedicationOrder;
import com.github.cainamott.nery_farma.impl.models.Movement;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MedicationOrderMapper {

    public MedicationOrder mapToEntity(MedicationOrderRequestDTO dto,
                                       Medication medication,
                                       User requester) {

        if (dto == null) {
            return null;
        }

        return MedicationOrder.builder()
                .medication(medication)
                .requestedQuantity(dto.requestedQuantity())
                .reason(dto.reason())
                .status(MedicationOrderStatus.PENDING)
                .requester(null)
                .movement(null)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public MedicationOrderDTO mapToDto(MedicationOrder order) {

        if (order == null) {
            return null;
        }

        Medication medication = order.getMedication();
        //User       requester  = order.getRequester();
        Movement   movement   = order.getMovement();

        return new MedicationOrderDTO(
                order.getId(),

                // dados do medicamento
                medication != null ? medication.getId()             : null,
                medication != null ? order.getRequestedQuantity() : null,

                order.getReason(),
                order.getStatus(),

                // dados do solicitante
                //requester != null ? null : null,
                null,

                // dados resumidos da movimentação vinculada
                movement != null ? movement : null,
                order.getCreatedAt()
        );
    }
}