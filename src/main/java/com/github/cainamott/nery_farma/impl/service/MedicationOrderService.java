package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.*;
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

    public void receiveCargoMovementUpdate(UpdateOrderRequest updateRequest) {

        // request de update de cargo com a situação atual e qual a atualização
        // basicamente update em banco
    }


    public MovementDTO receiveOrder(OrderReceivementRequest receivementRequest) {

        // recebe request

        // buscar pela order da request

        // checar o estado da order (se estiver ok vai para o prox passo)

        // update para concluido

        // gera entrada de estoque com estado intermediario esperando conferencia

        return null;

    }



}
