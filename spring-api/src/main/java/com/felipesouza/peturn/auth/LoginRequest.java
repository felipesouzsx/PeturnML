package com.felipesouza.peturn.auth;

public record LoginRequest(
        String email,
        String password
) {
}
