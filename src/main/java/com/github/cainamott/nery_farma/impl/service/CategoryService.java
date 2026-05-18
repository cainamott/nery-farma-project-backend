package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.mappers.CategoryMapper;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private static CategoryRepository repository;

    @Autowired
    private static CategoryMapper mapper;

    public void createCategory(CategoryDTO categoryDTO){
        if(!(repository.findByLabel(categoryDTO.label()) == null)){

            Category newCategory = mapper.mapToEntity(categoryDTO);
            newCategory.setId(UUID.randomUUID());
            newCategory.setCreationDate(Date.from(Instant.now()));
            repository.save(newCategory);

        } else throw new RuntimeException("Nome de categoria já existente");
    }

    public List<Category> listAllCategories(){
        return repository.findAll();
    }

    public CategoryDTO findCategoryByLabel(String label){

        Category categoryFound = repository.findByLabel(label);

        if(!(categoryFound == null)){

            return mapper.mapToDto(categoryFound);

        } else throw new RuntimeException("Nome de categoria não encontrado");
    }

    public void deleteCategory(String label) {
        
    }
}
