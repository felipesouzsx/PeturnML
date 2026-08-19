package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.Pet;

public record PostDTO(
        String name,
        String title,
        String description,
        String imageId,
        Pet pet
) {}
