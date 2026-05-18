package com.github.cainamott.nery_farma.impl.mappers;

import com.github.cainamott.nery_farma.impl.dto.CreateUserDTO;
import com.github.cainamott.nery_farma.impl.dto.UserDTO;
import com.github.cainamott.nery_farma.impl.enums.UserProfile;
import com.github.cainamott.nery_farma.impl.models.User;
import org.springframework.stereotype.Component;

import static com.github.cainamott.nery_farma.impl.enums.UserProfile.fromApi;

@Component
public class UserMapper {

    public UserDTO mapToDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getHashPassword(),
                user.getRegistryNumber(),
                user.getProfile().toApi(),
                user.getActive()
        );
    }

     public User mapToEntity(UserDTO userDTO) {
         if (userDTO == null) {
             return null;
         }
         return new User(
                 userDTO.id(),
                 userDTO.name(),
                 userDTO.email(),
                 userDTO.hashPassword(),
                 userDTO.registryNumber(),
                 fromApi(userDTO.profile()),
                 userDTO.active()
         );
     }

}
