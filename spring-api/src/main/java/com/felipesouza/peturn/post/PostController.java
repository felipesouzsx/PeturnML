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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@Slf4j
public class PostController {
    private PostService postService;
    private final int PAGE_SIZE = 10;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createPost(@Valid @ModelAttribute CreatePostRequest request, Authentication authentication) {
        log.info("Creating post");
        try {
            UUID userId = UUID.fromString(authentication.getName());
            postService.createPost(request, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> getPosts(@RequestParam(defaultValue = "0") int page, Authentication authentication) {
        log.info("Retrieving posts from others");
        try {
            Pageable pageable = PageRequest.of(page, PAGE_SIZE);
            UUID userId = UUID.fromString(authentication.getName());
            return ResponseEntity.ok(postService.getPostsFromOthers(userId, pageable));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Page<PostDTO>> getOwnPosts(@RequestParam(defaultValue = "0") int page, Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());

        log.info("Retrieving posts from user {}", userId);
        try {
            Pageable pageable = PageRequest.of(page, PAGE_SIZE);
            return ResponseEntity.ok(postService.getPostsByUserId(userId, pageable));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
