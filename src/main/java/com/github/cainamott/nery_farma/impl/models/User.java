package com.github.cainamott.nery_farma.impl.models;

import com.github.cainamott.nery_farma.impl.enums.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    UUID id ;
    String name;
    String senha_hash;
    String email;
    Integer num_registro;
    UserProfile perfil;
    Boolean ativo;
}

