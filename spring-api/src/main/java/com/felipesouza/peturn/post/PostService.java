package com.felipesouza.peturn.post;

import com.felipesouza.exceptions.MLServiceException;
import com.felipesouza.exceptions.PostNotFoundException;
import com.felipesouza.peturn.machineLearning.MLService;
import com.felipesouza.peturn.pet.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final MLService mlService;


    public PostEntity getPost(UUID postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    public Page<PostDTO> getPosts(Pageable pageable) {
        return postRepository
                .findAll(pageable)
                .map(postMapper::toDTO);
    }

    @Transactional(rollbackFor = MLServiceException.class)
    public void createPost(CreatePostRequest request) throws MLServiceException {
        Pet newPet = new Pet(request.petType(), request.petName());
        PostEntity newPost = PostEntity.builder()
                .pet(newPet)
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .build();
        postRepository.save(newPost);

        try {
            mlService.createImage(request.petImage(), newPost);
        } catch (ResourceAccessException | RestClientResponseException e) {
            throw new MLServiceException("ML Service couldn't be accessed");
        }
    }
}
