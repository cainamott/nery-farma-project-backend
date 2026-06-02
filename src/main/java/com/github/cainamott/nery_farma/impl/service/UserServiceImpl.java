package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.CreateUserDTO;
import com.github.cainamott.nery_farma.impl.models.User;
import com.github.cainamott.nery_farma.impl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.github.cainamott.nery_farma.impl.enums.UserProfile.fromApi;

@Service
public class UserServiceImpl implements  UserDetailsService {

    public UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(CreateUserDTO createUserDTO) {
        if(createUserDTO != null) {
            User user = new User(
                    UUID.randomUUID(),
                    createUserDTO.name(),
                    createUserDTO.email(),
                    createUserDTO.hashPassword(),
                    createUserDTO.registryNumber(),
                    fromApi(createUserDTO.profile()),
                    true
            );
            userRepository.save(user);
        }
    }

    public void deleteUser(UUID id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
