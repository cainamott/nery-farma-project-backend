package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.dto.CreateCategoryRequest;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neryfarma/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createNewCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){

        return ResponseEntity.status(201).body(categoryService.createCategory(createCategoryRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> listAllCategories() { // Realizar paginação no futuro CRUD basico no momento

        return ResponseEntity.status(201).body(categoryService.listAllCategories());
    }

    @GetMapping("/{label}")   // finalizar
    public ResponseEntity<CategoryDTO> findCategoryByLabel(@NotBlank @RequestParam("label") String label) {

        return ResponseEntity.status(200).body(categoryService.findCategoryByLabel(label));

    }
}
