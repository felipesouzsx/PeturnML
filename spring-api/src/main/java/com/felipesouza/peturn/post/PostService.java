package com.felipesouza.peturn.post;

import com.felipesouza.peturn.pet.Pet;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void getPost() {

    }

    public void createPost(CreatePostRequest request) {
        Pet newPet = new Pet(request.petType(), request.petName());
        PostEntity newPost = PostEntity.builder()
                .pet(newPet)
                .title(request.title())
                .description(request.description())
                .build();
        postRepository.saveAndFlush(newPost);
    }
}
