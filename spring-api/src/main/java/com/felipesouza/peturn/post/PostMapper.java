package com.felipesouza.peturn.post;

import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostDTO toDTO(PostEntity post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getImage().getId(),
                post.getUser().getId(),
                post.getStatus(),
                post.getPet()
        );
    }
}
