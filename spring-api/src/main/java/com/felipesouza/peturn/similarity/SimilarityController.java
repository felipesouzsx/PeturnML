package com.felipesouza.peturn.similarity;


import com.felipesouza.exceptions.PostNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/similarity")

public class SimilarityController {
    SimilarityService similarityService;


    public SimilarityController(SimilarityService similarityService) {
        this.similarityService = similarityService;
    }


    @GetMapping(params = "postId")
    public ResponseEntity<List<SimilarityDTO>> getSimilar(@RequestParam UUID postId) {
        try {
            List<SimilarityDTO> result = similarityService.getSimilar(postId);
            return ResponseEntity.ok(result);
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
