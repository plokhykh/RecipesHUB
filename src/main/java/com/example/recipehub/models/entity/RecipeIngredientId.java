package com.example.recipehub.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientId implements Serializable {
    private static final long serialVersionUID = -1L;
    private int recipe_id;
    private int ingredient_id;
}
