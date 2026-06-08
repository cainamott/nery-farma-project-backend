package com.github.cainamott.nery_farma.auth.repository;

import com.github.cainamott.nery_farma.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);
}
