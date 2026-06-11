package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.CreateMedicationDTO;
import com.github.cainamott.nery_farma.impl.dto.MedicationDTO;
import com.github.cainamott.nery_farma.impl.service.MedicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neryfarma/api/medications")
@AllArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/create")
    public ResponseEntity<MedicationDTO> createMedication(@RequestBody @Valid CreateMedicationDTO request) {


        return ResponseEntity.status(201).body(medicationService.createMedication(request));
}

    @GetMapping("/search")
    public ResponseEntity<MedicationDTO> findMedicationByName(@RequestParam("medicationName") @NotBlank String name) {

        return ResponseEntity.status(200).body(medicationService.findByMedicationName(name));
    }

    @GetMapping("/search/all")
    public ResponseEntity<Page<MedicationDTO>> findAllMedications(
            @PageableDefault(size = 10, sort = "medicationName", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(medicationService.findAllMedications(pageable));
    }

}
