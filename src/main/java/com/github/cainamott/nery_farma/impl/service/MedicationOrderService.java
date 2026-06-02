package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.MedicationOrderDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationOrderRequestDTO;
import com.github.cainamott.nery_farma.impl.mappers.MedicationOrderMapper;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.models.MedicationOrder;
import com.github.cainamott.nery_farma.impl.repository.MedicationOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicationOrderService {

    private final MedicationService medicationService;
    private final MedicationOrderRepository orderRepository;
    private static MedicationOrderMapper orderMapper;

    public MedicationOrderDTO createNewOrder(MedicationOrderRequestDTO requestDTO) {

        Medication medication = medicationService.findById(requestDTO.medicationId()).get();

        if (medicationService.validateMedication(requestDTO.medicationId()) == Boolean.FALSE) {
            throw new RuntimeException("Medicamento não está ativo!");
        }

        MedicationOrder medicationOrder = orderMapper.mapToEntity(requestDTO, medication,null);
        orderRepository.save(medicationOrder);

        return orderMapper.mapToDto(medicationOrder);
    }



}
