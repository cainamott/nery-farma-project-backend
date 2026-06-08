package com.github.cainamott.nery_farma.auth.controller;

import com.github.cainamott.nery_farma.auth.dto.AuthResponseDTO;
import com.github.cainamott.nery_farma.auth.dto.LoginRequestDTO;
import com.github.cainamott.nery_farma.auth.models.User;
import com.github.cainamott.nery_farma.auth.service.JwtService;
import com.github.cainamott.nery_farma.auth.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/neryfarma/api/auth")
public class AuthController {

    public final AuthenticationManager authenticationManager;
    public final UserDetailsServiceImpl userDetailsService;
    public final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity login(LoginRequestDTO loginRequestDTO){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.password()
                )
        );

        UserDetails details = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(details);
        Date expiresIn = jwtService.extractExpiration(token);

        User user = (User) details;

        AuthResponseDTO response =  new AuthResponseDTO(
                token,
                "Bearer",
                expiresIn,
                user.getEmail(),
                user.getProfile()
        );
        return ResponseEntity.ok(response);
    }
}

