package com.felipesouza.peturn.post;

import com.felipesouza.peturn.machineLearning.ImageEntity;
import com.felipesouza.peturn.pet.Pet;
import com.felipesouza.peturn.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="posts")

public class PostEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    UUID id;

    @Embedded
    Pet pet;

    @Enumerated(EnumType.STRING)
    PostStatus status;

    String title;
    String description;

    @OneToOne(mappedBy = "post")
    ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
}
