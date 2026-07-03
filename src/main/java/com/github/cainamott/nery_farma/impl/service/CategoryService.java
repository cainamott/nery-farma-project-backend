package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.dto.CreateCategoryRequest;
import com.github.cainamott.nery_farma.impl.exceptions.CategoryAlreadyExistsException;
import com.github.cainamott.nery_farma.impl.exceptions.CategoryNotFoundException;
import com.github.cainamott.nery_farma.impl.mappers.CategoryMapper;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public Category createCategory(CreateCategoryRequest request) {
        if (repository.findByLabelIgnoreCase(request.label()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = Category.builder()
                .label(request.label())
                .description(request.description())
                .creationDate(LocalDate.now())
                .build();

        return repository.save(category);
    }

    public List<Category> listAllCategories() {
        return repository.findAll();
    }

    public CategoryDTO findCategoryByLabel(String label) {
        Category category = repository.findByLabelIgnoreCase(label)
                .orElseThrow(CategoryNotFoundException::new);

        return mapper.mapToDto(category);
    }

    public Category findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public void deleteCategory(String label) {
    }
}
