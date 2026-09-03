package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.PetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostRequest(
   @NotBlank @Size(max = 50) String petName,
   PetType petType,
   @NotBlank @Size(max = 100) String title,
   @NotNull @Size(max = 500) String description,
   @NotNull MultipartFile petImage,
   PostStatus status
) {}
