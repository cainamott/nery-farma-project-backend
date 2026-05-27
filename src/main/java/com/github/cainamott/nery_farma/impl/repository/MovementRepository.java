package com.github.cainamott.nery_farma.impl.repository;

import com.github.cainamott.nery_farma.impl.dto.MovementDTO;
import com.github.cainamott.nery_farma.impl.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovementRepository extends JpaRepository<Movement, UUID> {
}
