package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.Pet;
import com.felipesouza.peturn.user.UserDTO;

import java.util.UUID;

public record PostDTO(
        UUID id,
        String title,
        String description,
        String imageFilename,
        UserDTO user,
        PostStatus status,
        Pet pet
) {}
