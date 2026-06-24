package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CreateMedicationDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationDTO;
import com.github.cainamott.nery_farma.impl.exceptions.MedicationAlreadyExistsException;
import com.github.cainamott.nery_farma.impl.exceptions.MedicationNotFoundException;
import com.github.cainamott.nery_farma.impl.mappers.MedicationMapper;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicationService {

    private final MedicationRepository repository;
    private final MedicationMapper mapper;
    private final CategoryService categoryService;

    public Page<MedicationDTO> findAllMedications(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::mapToDto);
    }

    public MedicationDTO createMedication(CreateMedicationDTO medicationDto) {
        ensureNameIsAvailable(medicationDto.medicationName());

        Medication medication = buildMedication(medicationDto);
        repository.save(medication);

        return mapper.mapToDto(medication);
    }

    public MedicationDTO findByMedicationName(String name) {
        requireNonBlankName(name);
        return mapper.mapToDto(findMedicationByName(name));
    }

    public void deleteMedication(String name) {
        requireNonBlankName(name);
        repository.delete(findMedicationByName(name));
    }

    public MedicationDTO updateMedication(UUID id, CreateMedicationDTO medicationDto) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }

        return repository.findById(id)
                .map(existing -> saveUpdatedMedication(id, medicationDto))
                .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado"));
    }

    public Boolean validateMedication(UUID medicationId) {
        Medication medication = findById(medicationId)
                .orElseThrow(() -> new MedicationNotFoundException(medicationId));

        return medication.getIsActive();
    }

    public Optional<Medication> findById(UUID id) {
        return repository.findById(id);
    }

    private Medication buildMedication(CreateMedicationDTO medicationDto) {
        return Medication.builder()
                .medicationName(medicationDto.medicationName())
                .category(categoryService.findById(medicationDto.categoryId()))
                .activePrinciple(medicationDto.activePrinciple())
                .dosageForm(medicationDto.dosageForm())
                .unitary(medicationDto.unitary())
                .insertionDate(LocalDate.now())
                .isActive(medicationDto.isActive())
                .build();
    }

    private MedicationDTO saveUpdatedMedication(UUID id, CreateMedicationDTO medicationDto) {
        MedicationDTO updatedDto = new MedicationDTO(
                id,
                medicationDto.medicationName(),
                categoryService.findById(medicationDto.categoryId()),
                medicationDto.activePrinciple(),
                medicationDto.dosageForm(),
                medicationDto.unitary(),
                medicationDto.minimalStorage(),
                LocalDate.now(),
                medicationDto.isActive()
        );

        Medication updatedMedication = mapper.mapToEntity(updatedDto);
        return mapper.mapToDto(repository.save(updatedMedication));
    }

    private Medication findMedicationByName(String name) {
        return repository.findByMedicationNameIgnoreCase(name)
                .orElseThrow(() -> new MedicationNotFoundException());
    }

    private void ensureNameIsAvailable(String name) {
        if (repository.findByMedicationNameIgnoreCase(name).isPresent()) {
            throw new MedicationAlreadyExistsException();
        }
    }

    private void requireNonBlankName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
    }
}
