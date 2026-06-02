package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.MedicationOrderDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationOrderRequestDTO;
import com.github.cainamott.nery_farma.impl.service.MedicationOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neryfarma/api/orders")
@AllArgsConstructor
public class MedicationOrderController {

    private final MedicationOrderService medicationOrderService;

    public ResponseEntity<MedicationOrderDTO> createNewMedicationOrder(MedicationOrderRequestDTO requestDTO) {

        return ResponseEntity.ok(medicationOrderService.createNewOrder(requestDTO));
    }
}
