package com.felipesouza.peturn.post;

import com.felipesouza.peturn.machineLearning.ImageEntity;
import com.felipesouza.peturn.pet.Pet;
import jakarta.persistence.*;
import lombok.*;

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
    String id;

    @Embedded
    Pet pet;

    String title;
    String description;

    @OneToOne(mappedBy = "post")
    ImageEntity image;
}
