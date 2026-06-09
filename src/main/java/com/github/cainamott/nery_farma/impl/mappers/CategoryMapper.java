package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private static CategoryRepository repository;

    public Category mapToEntity(CategoryDTO categoryDto) {

        if (categoryDto == null) {
            return null;
        }
        return new Category(
                categoryDto.id(),
                categoryDto.label(),
                categoryDto.description(),
                categoryDto.creationDate()
        );
    }


    public CategoryDTO mapToDto(Category category){

        if (category == null) {
            return null;
        }

        return new CategoryDTO(
                category.getId(),
                category.getLabel(),
                category.getDescription(),
                category.getCreationDate()
        );
    }

}
