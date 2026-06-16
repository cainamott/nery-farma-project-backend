package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockDispatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/neryfarma/api/movement")
@RestController
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @PostMapping("/entry")
    public ResponseEntity<BatchDTO> medicationEntryMovement(@RequestBody StockEntryDTO entryDTO) {
        return ResponseEntity.accepted().body(movementService.stockEntry(entryDTO));
    }


    @PostMapping("/dispatch")
    public ResponseEntity medicationDispatchMovement(StockDispatchDTO dispatchDTO) {
        movementService.stockDispatch(dispatchDTO);
        return null;
    }
}
