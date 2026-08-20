package com.felipesouza.peturn.similarity;

import com.felipesouza.exceptions.PostNotFoundException;
import com.felipesouza.peturn.machineLearning.MLService;
import com.felipesouza.peturn.post.PostEntity;
import com.felipesouza.peturn.post.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SimilarityService {
        PostService postService;
        MLService mlService;


        public SimilarityService(PostService postService, MLService mlService) {
            this.postService = postService;
            this.mlService = mlService;
        }


        public List<SimilarityDTO> getSimilar(UUID postId) throws PostNotFoundException {
            PostEntity queryPost = postService.getPost(postId);
            return mlService.getSimilar(queryPost);
        }
}
