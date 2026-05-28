package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.MovementDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/neryfarma/api/movement")
@RestController
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping("/entry")
    public ResponseEntity medicationEntryMovement(StockEntryDTO entryDTO) {
        movementService.stockEntry(entryDTO);
        return ResponseEntity.accepted().build();
    }
}
