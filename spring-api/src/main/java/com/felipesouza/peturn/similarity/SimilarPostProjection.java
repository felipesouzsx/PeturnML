package com.felipesouza.peturn.similarity;

import com.felipesouza.peturn.pet.PetType;
import com.felipesouza.peturn.post.PostStatus;

import java.util.UUID;

public interface SimilarPostProjection {
    UUID getId();
    String getTitle();
    String getDescription();
    String getImageFilename();
    Float getSimilarity();
    PostStatus getStatus();
}
