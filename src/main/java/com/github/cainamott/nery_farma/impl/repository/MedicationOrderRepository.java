package com.github.cainamott.nery_farma.impl.repository;

import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.models.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, UUID> {

}
