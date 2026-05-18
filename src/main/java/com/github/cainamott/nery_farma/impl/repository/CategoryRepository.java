package com.github.cainamott.nery_farma.impl.repository;

import com.github.cainamott.nery_farma.impl.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    public Category findByLabel(String label);
}
