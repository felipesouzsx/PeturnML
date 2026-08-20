package com.felipesouza.peturn.similarity;

import com.felipesouza.peturn.post.PostDTO;

public record SimilarityDTO(
        PostDTO post,
        float similarity
) {}
