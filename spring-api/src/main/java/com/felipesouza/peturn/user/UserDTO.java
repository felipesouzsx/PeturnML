package com.felipesouza.peturn.user;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String username,
        String email
) { }
