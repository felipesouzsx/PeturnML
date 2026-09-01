package com.felipesouza.peturn.auth;

import com.felipesouza.peturn.user.UserDTO;

public record LoginData(
        String token,
        UserDTO user
) {
}