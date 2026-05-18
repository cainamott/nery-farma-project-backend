package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.CategoryDTO;
import com.github.cainamott.nery_farma.impl.dto.UserDTO;
import com.github.cainamott.nery_farma.impl.models.Category;
import com.github.cainamott.nery_farma.impl.models.User;
import org.springframework.stereotype.Component;

import static com.github.cainamott.nery_farma.impl.enums.UserProfile.fromApi;

@Component
public class CategoryMapper {

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
