package com.example.recipehub.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor

@Getter
@Setter
public class IngredientQuantity {
    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipe_id")
    private Recipe recipe;

    @Column
    private int quantity;

    public IngredientQuantity(Recipe recipe, Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
    }
}
