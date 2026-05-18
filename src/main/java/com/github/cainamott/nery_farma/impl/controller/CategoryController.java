package com.github.cainamott.nery_farma.impl.controller;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neryfarma/api/category")
public class CategoryController {

    @Autowired
    private static CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createNewCategory(CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAllCategories() { // Realizar paginação no futuro CRUD basico no momento

        return ResponseEntity.ok(categoryService.listAllCategories());
    }

    @GetMapping()
    public ResponseEntity<CategoryDTO> findCategoryByLabel(@RequestParam("label") String label) {

        categoryService.findCategoryByLabel(label);
    }
}
