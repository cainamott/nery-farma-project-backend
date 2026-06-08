package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
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

    private static CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> listAllCategories() { // Realizar paginação no futuro CRUD basico no momento

        return ResponseEntity.ok(categoryService.listAllCategories());
    }

    @GetMapping()   // finalizar
    public ResponseEntity<CategoryDTO> findCategoryByLabel(@NotBlank @RequestParam("label") String label) {

        return ResponseEntity.status(200).body(categoryService.findCategoryByLabel(label));

    }
}
