package com.felipesouza.peturn.machineLearning;

import com.felipesouza.peturn.post.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "images")

public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Column(columnDefinition = "vector(512)")
    private float[] embedding;

    @OneToOne
    @JoinColumn(name = "post_id", unique = true)
    private PostEntity post;
}
