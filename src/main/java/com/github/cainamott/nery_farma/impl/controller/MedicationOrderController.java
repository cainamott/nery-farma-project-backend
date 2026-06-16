package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.MedicationOrderDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationOrderRequest;
import com.github.cainamott.nery_farma.impl.dto.UpdateOrderRequest;
import com.github.cainamott.nery_farma.impl.dto.UpdateOrderResponse;
import com.github.cainamott.nery_farma.impl.service.MedicationOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neryfarma/api/orders")
@AllArgsConstructor
public class MedicationOrderController {

    private final MedicationOrderService medicationOrderService;

    @PostMapping("/new-order")
    public ResponseEntity<MedicationOrderDTO> createNewMedicationOrder(
            @RequestBody MedicationOrderRequest medicationOrderRequest) {

        return ResponseEntity.status(201).body(medicationOrderService.createNewOrder(medicationOrderRequest));
    }

    @PostMapping("/update-order-status")
    public ResponseEntity<UpdateOrderResponse> updateOrderStatus(
            @RequestBody UpdateOrderRequest updateOrderRequest) {

        return ResponseEntity.accepted().body(medicationOrderService.receiveCargoMovementUpdate(updateOrderRequest));
    }

}
