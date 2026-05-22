package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.MedicationDTO;
import com.github.cainamott.nery_farma.impl.models.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {


    public Medication mapToEntity(MedicationDTO medicationDTO) {

        if (medicationDTO == null) {
            return null;
        }
        return new Medication(
                medicationDTO.id(),
                medicationDTO.medicationName(),
                medicationDTO.categoryId(),
                medicationDTO.activePrinciple(),
                medicationDTO.dosageForm(),
                medicationDTO.unitary(),
                medicationDTO.minimalStorage(),
                medicationDTO.insertionDate(),
                medicationDTO.isActive()
        );
    }


    public MedicationDTO mapToDto(Medication medication){

        if (medication == null) {
            return null;
        }

        return new MedicationDTO(
                medication.getId(),
                medication.getMedicationName(),
                medication.getCategory(),
                medication.getActivePrinciple(),
                medication.getDosageForm(),
                medication.getUnitary(),
                medication.getMinimalStorage(),
                medication.getInsertionDate(),
                medication.getIsActive()
        );
    }
}
