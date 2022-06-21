package com.example.recipehub.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class CategoryRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int parent;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "category_recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();

    public CategoryRecipe(int parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public CategoryRecipe(int id, int parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }
}
