package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public void createCategory(CategoryDTO categoryDTO){
        if(!(repository.findByLabel(categoryDTO.label()).isBlank())){
            Category newCategory = new Category();
            newCategory.setId(UUID.randomUUID());
            newCategory.setLabel(categoryDTO.label());
            newCategory.setDescription(categoryDTO.description());
            newCategory.setCreationDate(Date.from(Instant.now()));

        } else throw new RuntimeException("Nome de categoria já existente");
    }

}
