package com.felipesouza.peturn.similarity;

import com.felipesouza.peturn.pet.PetType;

import java.util.UUID;

public interface SimilarPostProjection {
    UUID getId();
    String getTitle();
    String getDescription();
    String getImageId();
    String getPetName();
    PetType getPetType();
    Float getSimilarity();
}
