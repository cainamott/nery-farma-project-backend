package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.repository.UserRepository;
import com.github.cainamott.nery_farma.impl.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public User loadUserByUsername(String email){

        var user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("Email não encontrado");
        }
        return user;
    }
}
