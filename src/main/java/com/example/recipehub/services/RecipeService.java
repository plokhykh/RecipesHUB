package com.example.recipehub.services;

import com.example.recipehub.dao.RecipeDAO;

import com.example.recipehub.models.RecipePage;
import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService {
    final String path = System.getProperty("user.home") + File.separator + "imageMeals" + File.separator;
    private RecipeDAO recipeDAO;
    IngredientService ingredientService;
    CategoryRecipeService categoryRecipeService;
    HelperService helperService;

    public RecipeWithIngredientsDTO createRecipe(RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException {
        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(id -> categoryRecipeService.findCategoryRecipe(id))
                .collect(Collectors.toList());

        Recipe newRecipe = new Recipe(
                recipe.getTitle(),
                recipe.getDescription(),
                image.getOriginalFilename(),
                categories,
                recipe.getAuthor()
        );

        recipe.getIngredients()
                .stream()
                .forEach(item -> {
                    Ingredient ingredient = ingredientService.findFullIngredient(item.getIngredient_id());
                    IngredientDTO calculatedIngredient = new IngredientDTO().calculateNutrientsInIngredient(ingredient, item.getWeight());
                    newRecipe.addIngredient(ingredient, calculatedIngredient);
                });

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(categories);

        RecipeWithIngredientsDTO recipeWithIngredientsDTO = new RecipeWithIngredientsDTO(
                recipeDAO.save(newRecipe),
                categoryRecipeWithSubcategoriesDTOS
        );

        image.transferTo(new File(path + image.getOriginalFilename()));

        return recipeWithIngredientsDTO;
    }


    public ResponseEntity<PageImpl<RecipeWithIngredientsDTO>> getAllRecipes(RecipePage recipePage) {
        Pageable pageable = PageRequest.of(recipePage.getPage() - 1, recipePage.getSize());
        Page<Recipe> recipes = recipeDAO.findAll(pageable);

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

    public ResponseEntity<RecipeWithIngredientsDTO> updateRecipeById(int id, RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException {
        Optional<Recipe> checkRecipe = recipeDAO.findById(id);

        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(category_id -> categoryRecipeService.findCategoryRecipe(category_id))
                .collect(Collectors.toList());

        Recipe updateRecipe = new Recipe(
                id,
                recipe.getTitle(),
                recipe.getDescription(),
                image.getOriginalFilename(),
                categories,
                recipe.getAuthor()
        );

//        recipe.getIngredients().stream().forEach(item -> {
//            Ingredient ingredient = ingredientService.findFullIngredient(item.getIngredient_id());
//            updateRecipe.addIngredient(ingredient, item.getWeight());
//        });

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(categories);

        RecipeWithIngredientsDTO recipeWithIngredientsDTO = new RecipeWithIngredientsDTO(
                recipeDAO.save(updateRecipe),
                categoryRecipeWithSubcategoriesDTOS
        );

        image.transferTo(new File(path + image.getOriginalFilename()));

        return new ResponseEntity<>(
                recipeWithIngredientsDTO,
                HttpStatus.OK);
    }


    public void deleteRecipe(int id) {
        recipeDAO.deleteById(id);
    }
}
