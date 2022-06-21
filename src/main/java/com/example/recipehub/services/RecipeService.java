package com.example.recipehub.services;

import com.example.recipehub.dao.RecipeDAO;

import com.example.recipehub.models.RecipePage;
import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.CategoryRecipe;
import com.example.recipehub.models.entity.Ingredient;
import com.example.recipehub.models.entity.Recipe;
import com.example.recipehub.services.helper.HelperService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {
    private RecipeDAO recipeDAO;
    IngredientService ingredientService;
    CategoryRecipeService categoryRecipeService;
    HelperService helperService;

    public RecipeWithIngredientsDTO createRecipe(RecipeWithIngredientsIdDTO recipe) {
        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(id -> categoryRecipeService.findCategoryRecipe(id))
                .collect(Collectors.toList());

        Recipe newRecipe = new Recipe(
                recipe.getTitle(),
                recipe.getDescription(),
                categories,
                recipe.getAuthor()
        );

        recipe.getIngredients()
                .stream()
                .forEach(item -> {
                    Ingredient ingredient = ingredientService.findFullIngredient(item.getIngredient_id());
                    newRecipe.addIngredient(ingredient, item.getQuantity());
                });

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(categories);

        return new RecipeWithIngredientsDTO(
                recipeDAO.save(newRecipe),
                categoryRecipeWithSubcategoriesDTOS
        );
    }

    public ResponseEntity<PageImpl<RecipeWithIngredientsDTO>> getAllRecipes(RecipePage recipePage) {
        Pageable pageable = PageRequest.of(recipePage.getPage(), recipePage.getSize());
        Page<Recipe> recipes = recipeDAO.findAll(pageable);

        if (recipes == null) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<RecipeWithIngredientsDTO> recipesList = recipes.getContent()
                .stream()
                .map(item -> {
                    List<CategoryRecipeWithSubcategoriesDTO> categoryRecipes = helperService.transformToListCategoryWithSubcategories(item.getCategories());
                    return new RecipeWithIngredientsDTO(item, categoryRecipes);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new PageImpl<>(recipesList, pageable, recipes.getTotalElements()),
                HttpStatus.OK
        );
    }


    public ResponseEntity<RecipeWithIngredientsDTO> getRecipeById(int id) {
        Recipe recipe = recipeDAO.findById(id).orElse(new Recipe());

        if (recipe == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(recipe.getCategories());

        return new ResponseEntity<>(new RecipeWithIngredientsDTO(recipe, categoryRecipeWithSubcategoriesDTOS), HttpStatus.OK);
    }


    public ResponseEntity<List<RecipeWithIngredientsDTO>> getRecipesByCategories(int id) {
        CategoryRecipe categoryById = categoryRecipeService.getCategoryById(id);
        List<RecipeWithIngredientsDTO> recipes = categoryById.getRecipes().stream().map(RecipeWithIngredientsDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    public ResponseEntity<RecipeWithIngredientsDTO> updateRecipeById(int id, RecipeWithIngredientsIdDTO recipe) {
        Optional<Recipe> checkRecipe = recipeDAO.findById(id);
        if (checkRecipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(category_id -> categoryRecipeService.findCategoryRecipe(category_id))
                .collect(Collectors.toList());

        Recipe updateRecipe = new Recipe(
                id,
                recipe.getTitle(),
                recipe.getDescription(),
                categories,
                recipe.getAuthor()
        );

        recipe.getIngredients().stream().forEach(item -> {
            Ingredient ingredient = ingredientService.findFullIngredient(item.getIngredient_id());
            updateRecipe.addIngredient(ingredient, item.getQuantity());
        });

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(categories);
        return new ResponseEntity<>(
                new RecipeWithIngredientsDTO(
                        recipeDAO.save(updateRecipe),
                        categoryRecipeWithSubcategoriesDTOS
                ),
                HttpStatus.OK);
    }


    public void deleteRecipe(int id) {
        recipeDAO.deleteById(id);
    }
}