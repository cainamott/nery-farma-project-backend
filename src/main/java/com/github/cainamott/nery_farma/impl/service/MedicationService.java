package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.MedicationDTO;
import com.github.cainamott.nery_farma.impl.dto.CreateMedicationDTO;
import com.github.cainamott.nery_farma.impl.mappers.MedicationMapper;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class MedicationService {

    private static MedicationRepository repository;

    private static MedicationMapper mapper;

    public void createMedication(CreateMedicationDTO medicationDto) {

        if((repository.findByMedicationNameIgnoreCase(medicationDto.medicationName()) == null)) {

            Medication medication = mapper.mapToEntity(new MedicationDTO(
                    UUID.randomUUID(),
                    medicationDto.medicationName(),
                    medicationDto.categoryId(),
                    medicationDto.activePrinciple(),
                    medicationDto.dosageForm(),
                    medicationDto.unitary(),
                    medicationDto.minimalStorage(),
                    Date.from(Instant.now()),
                    medicationDto.isActive()
            ));

            repository.save(medication);
        }
    }

    public MedicationDTO findByMedicationName(String name) {

        if(name == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }

        var medicationDto = mapper.mapToDto(repository.findByMedicationNameIgnoreCase(name));

    }

}
