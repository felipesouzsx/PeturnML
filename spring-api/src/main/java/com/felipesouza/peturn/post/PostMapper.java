package com.felipesouza.peturn.post;

import com.felipesouza.peturn.user.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostDTO toDTO(PostEntity post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getImage().getFilename(),
                UserMapper.toDTO(post.getUser()),
                post.getStatus(),
                post.getPet()
        );
    }
}
