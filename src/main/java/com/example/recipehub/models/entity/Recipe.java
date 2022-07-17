package com.example.recipehub.models.entity;

import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
//    @NotBlank(message = "Recipe title cannot be null")
//    @Min(value = 3, message = "Recipe title should not be less 3")
    private String title;
//    @NotBlank(message = "Description cannot be null")
//    @Size(min = 10, max = 50000, message = "Description must be between 10 and 50000 characters")
    private String description;
//    @NotBlank(message = "Image cannot be null")
    private String image;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_recipe_id")
    )
    private List<CategoryRecipe> categories;

//    @NotBlank(message = "Author cannot be null")
//    @Min(value = 3, message = "Author should not be less 3")
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


    public void addIngredient(Ingredient ingredient, IngredientDTO calculatedIngredient) {
        IngredientQuantity ingredientQuantity = new IngredientQuantity(this, ingredient, calculatedIngredient);
        ingredientQuantities.add(ingredientQuantity);
        ingredient.getIngredientQuantities().add(ingredientQuantity);

    }
}
