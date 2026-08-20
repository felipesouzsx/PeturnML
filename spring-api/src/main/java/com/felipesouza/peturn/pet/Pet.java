package com.felipesouza.peturn.pet;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Pet {
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    PetType type;

    @Column(name = "pet_name")
    String name;
}
