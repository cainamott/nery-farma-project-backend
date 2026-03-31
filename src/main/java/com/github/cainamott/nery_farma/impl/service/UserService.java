package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CreateUserDTO;
import com.github.cainamott.nery_farma.impl.models.User;
import com.github.cainamott.nery_farma.impl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.github.cainamott.nery_farma.impl.enums.UserProfile.fromApi;

@Service
public interface UserService {

    User findUserByEmail(String email);

    User findUserById(UUID id);

    List<User> findAllUsers();

    void createUser(CreateUserDTO createUserDTO);

    void deleteUser(UUID id);

     UserDetails loadUserByUsername(String username);
}
