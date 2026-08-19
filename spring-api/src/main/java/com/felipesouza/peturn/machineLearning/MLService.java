package com.felipesouza.peturn.machineLearning;

import com.felipesouza.exceptions.ImageEmbeddingException;
import com.felipesouza.peturn.post.PostEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@Service
public class MLService {
    private final ImageRepository imageRepository;
    private final RestClient restClient;


    public MLService(ImageRepository imageRepository, RestClient restClient) {
        this.imageRepository = imageRepository;
        this.restClient = restClient;
    }


    public void createImage(MultipartFile img, PostEntity post) {
        float[] embedding = this.getImageEmbedding(img);
        ImageEntity newImage = ImageEntity.builder()
                .post(post)
                .embedding(embedding)
                .build();
        imageRepository.saveAndFlush(newImage);
    }


    public float[] getImageEmbedding(MultipartFile image) throws ImageEmbeddingException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body.add("image", new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            });
        } catch (IOException e) {
            throw new ImageEmbeddingException();
        }

        EmbeddingResponse response = restClient
                .post().uri("/embedding")
                .contentType(MediaType.MULTIPART_FORM_DATA).body(body)
                .retrieve().body(EmbeddingResponse.class);

        if (
            response == null ||
            response.embedding() == null ||
            response.embedding().length != 512
        ) {
            throw new ImageEmbeddingException();
        }

        return response.embedding();
    }
}
