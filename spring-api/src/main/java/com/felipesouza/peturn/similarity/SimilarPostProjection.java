package com.felipesouza.peturn.similarity;

import com.felipesouza.peturn.pet.PetType;
import com.felipesouza.peturn.post.PostStatus;

import java.util.UUID;

public interface SimilarPostProjection {
    UUID getId();
    String getTitle();
    String getDescription();
    UUID getImageId();
    String getPetName();
    PetType getPetType();
    Float getSimilarity();
    PostStatus getStatus();
}
