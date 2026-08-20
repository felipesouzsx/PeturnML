package com.felipesouza.peturn.machineLearning;

import com.felipesouza.peturn.similarity.SimilarPostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

    // Essa query faz um cast pro SimilarPostProjection, organizando os valores num objeto só.
    // Fiz o uso do PGVector para calcular a distância entre o embedding das imagens direto no banco, sem depender da
    // API python.
    @Query(value = """
    SELECT p.id, p.title, p.description,
           p.pet_name AS petName,
           p.pet_type AS petType,
           i.id AS imageId,
           1 - (i.embedding <=> CAST(:queryEmbedding AS vector)) AS similarity
    FROM images i JOIN posts p ON p.id = i.post_id
    WHERE i.post_id <> :queryPostId
    ORDER BY i.embedding <=> CAST(:queryEmbedding AS vector)
    LIMIT :limit""", nativeQuery = true)
    List<SimilarPostProjection> findSimilarPosts(
        @Param("queryPostId") UUID queryPostId,
        @Param("queryEmbedding") float[] queryEmbedding,
        @Param("limit") int limit
    );
}
