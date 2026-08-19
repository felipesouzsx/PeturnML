package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostRequest(
   @NotBlank String petName,
   PetType petType,
   @NotBlank String title,
   @NotNull String description,
   @NotNull MultipartFile petImage
) {}
