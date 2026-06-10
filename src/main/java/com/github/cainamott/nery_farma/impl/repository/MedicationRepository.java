package com.github.cainamott.nery_farma.impl.repository;

import com.github.cainamott.nery_farma.impl.models.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

    Optional<Medication> findByMedicationNameIgnoreCase(String name);

    List<String> findByCategoryId(Integer id);

    Page<Medication> findAll(Pageable pageable);
}
