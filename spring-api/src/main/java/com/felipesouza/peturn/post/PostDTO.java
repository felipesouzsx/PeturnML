package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.Pet;

import java.util.UUID;

public record PostDTO(
        UUID id,
        String title,
        String description,
        String imageId,
        Pet pet
) {}
