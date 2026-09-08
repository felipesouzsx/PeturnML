package com.felipesouza.peturn.user;

public class UserMapper {
    public static UserDTO toDTO(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
