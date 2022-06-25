package com.example.recipehub.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String image;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_recipe_id")
    )
    private List<CategoryRecipe> categories;

    private String author;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<IngredientQuantity> ingredientQuantities = new ArrayList<>();


    public Recipe(String title, String description, String image, List<CategoryRecipe> categories, String author) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.categories = categories;
        this.author = author;
    }

    public Recipe(int id, String title, String description, String image, List<CategoryRecipe> categories, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.categories = categories;
        this.author = author;
    }


    public void addIngredient(Ingredient ingredient, int quantity) {
        IngredientQuantity ingredientQuantity = new IngredientQuantity(this, ingredient, quantity);
        ingredientQuantities.add(ingredientQuantity);
        ingredient.getIngredientQuantities().add(ingredientQuantity);
    }
}
