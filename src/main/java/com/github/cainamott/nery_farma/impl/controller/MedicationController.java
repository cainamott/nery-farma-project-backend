package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.CreateMedicationDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationDTO;
import com.github.cainamott.nery_farma.impl.service.MedicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neryfarma/api/medications")
public class MedicationController {

    private static MedicationService medicationService;


    @PostMapping("/create")
    public ResponseEntity createMedication(@Valid @RequestBody CreateMedicationDTO request) {

        medicationService.createMedication(request);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<MedicationDTO> findMedicationByName(@NotBlank @RequestParam("name") String name) {

        return ResponseEntity.status(200).body(medicationService.findByMedicationName(name));
    }

    @GetMapping
    public ResponseEntity<Page<MedicationDTO>> findAllMedications(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(medicationService.findAllMedications(pageable));
    }

}
