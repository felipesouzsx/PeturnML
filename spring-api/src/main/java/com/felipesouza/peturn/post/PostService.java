package com.felipesouza.peturn.post;

import com.felipesouza.exceptions.PostNotFoundException;
import com.felipesouza.peturn.machineLearning.MLService;
import com.felipesouza.peturn.pet.Pet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    PostRepository postRepository;
    MLService mlService;


    public PostService(PostRepository postRepository, MLService mlService) {
        this.postRepository = postRepository;
        this.mlService = mlService;
    }

    public PostEntity getPost(UUID postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    public void createPost(CreatePostRequest request) {
        Pet newPet = new Pet(request.petType(), request.petName());
        PostEntity newPost = PostEntity.builder()
                .pet(newPet)
                .title(request.title())
                .description(request.description())
                .build();
        postRepository.saveAndFlush(newPost);
        mlService.createImage(request.petImage(), newPost);
    }
}
