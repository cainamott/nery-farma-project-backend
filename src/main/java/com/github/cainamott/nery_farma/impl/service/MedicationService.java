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
import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository repository;
    private final MedicationMapper mapper;

    public MedicationService(MedicationRepository repository, MedicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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

        return mapper.mapToDto(repository.findByMedicationNameIgnoreCase(name));
    }

    public void deleteMedication(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }

        Medication medication = repository.findByMedicationNameIgnoreCase(name);

        if(medication != null) {
            repository.delete(medication);
        }
    }

    public MedicationDTO updateMedication(UUID id, CreateMedicationDTO medicationDto) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        return repository.findById(id).map(existing -> {
            Medication updatedMedication = mapper.mapToEntity(new MedicationDTO(
                    id,
                    medicationDto.medicationName(),
                    medicationDto.categoryId(),
                    medicationDto.activePrinciple(),
                    medicationDto.dosageForm(),
                    medicationDto.unitary(),
                    medicationDto.minimalStorage(),
                    Date.from(Instant.now()),
                    medicationDto.isActive()
            ));

            return mapper.mapToDto(repository.save(updatedMedication));
        }).orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado"));
    }

    public Boolean validateMedication(UUID medicationId) {

        if (findById(medicationId).isPresent()){
            return findById(medicationId).get().getIsActive();
        }
        else throw new RuntimeException("Medicamento não encontrado para essa Id");
    }

    public Optional<Medication> findById(UUID id) {

        return repository.findById(id);

    }
}
