package com.felipesouza.peturn.post;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Slf4j
public class PostController {
    private PostService postService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createPost(@Valid @ModelAttribute CreatePostRequest request) {
        log.info("Creating post");
        try {
            postService.createPost(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> getPosts(@RequestParam(defaultValue = "0") int page) {
        log.info("Retrieving posts");
        try {
            Pageable pageable = PageRequest.of(page, 10);
            return ResponseEntity.ok(postService.getPosts(pageable));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
