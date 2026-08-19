package com.felipesouza.peturn.post;

import com.felipesouza.peturn.machineLearning.MLService;
import com.felipesouza.peturn.pet.Pet;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository postRepository;
    MLService mlService;


    public PostService(PostRepository postRepository, MLService mlService) {
        this.postRepository = postRepository;
        this.mlService = mlService;
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
        mlService.createImage(request.petImage(), newPost);
    }
}
