package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.MovementDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/neryfarma/api/movement")
@RestController
public class MovementController {

    @PostMapping("/entry")
    public ResponseEntity medicationEntryMovement(StockEntryDTO entryDTO) {


    }
}
