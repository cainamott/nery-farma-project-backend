package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.dto.CreateCategoryRequest;
import com.github.cainamott.nery_farma.impl.mappers.CategoryMapper;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {


    private final CategoryRepository repository;
    private CategoryMapper mapper;

    public Category createCategory(CreateCategoryRequest createCategoryRequest){
        if(repository.findByLabelIgnoreCase(createCategoryRequest.label()).isPresent()) {
            throw new RuntimeException("Nome de categoria já existente");
        }
            return repository.save(Category.builder()
                    .label(createCategoryRequest.label())
                    .description(createCategoryRequest.description())
                    .creationDate(LocalDate.now())
                    .build());

    }

    public List<Category> listAllCategories(){
        return repository.findAll();
    }

    public CategoryDTO findCategoryByLabel(String label){

        Category categoryFound = repository.findByLabelIgnoreCase(label).get();

        if(!(categoryFound == null)){

            return mapper.mapToDto(categoryFound);

        } else throw new RuntimeException("Nome de categoria não encontrado");
    }

    public Category findById(UUID id) {

        return repository.findById(id).get();
    }

    public void deleteCategory(String label) {
        
    }
}
