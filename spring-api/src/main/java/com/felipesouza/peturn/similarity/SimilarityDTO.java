package com.felipesouza.peturn.similarity;

import com.felipesouza.peturn.post.PostStatus;

import java.util.UUID;

public record SimilarityDTO(
        UUID postId,
        String postTitle,
        String postDescription,
        String imageFilename,
        PostStatus status,
        float similarity
) {}
