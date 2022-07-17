package com.example.recipehub.services.recipe;

import com.example.recipehub.dao.RecipeDAO;

import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.CategoryRecipe;
import com.example.recipehub.models.entity.Ingredient;
import com.example.recipehub.models.entity.Recipe;
import com.example.recipehub.services.categoryRecipe.ICategoryRecipeService;
import com.example.recipehub.services.ingredient.IIngredientService;
import com.example.recipehub.services.helper.HelperService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeService implements IRecipeService {
    final String path = System.getProperty("user.home") + File.separator + "imageMeals" + File.separator;
    private RecipeDAO recipeDAO;
    private IIngredientService ingredientService;
    private ICategoryRecipeService categoryRecipeService;
    private HelperService helperService;


    @Override
    public PageImpl<RecipeWithIngredientsDTO> getAll(DefaultPage recipePage, String q) {
        Pageable pageable = PageRequest.of(recipePage.getPage() - 1, recipePage.getSize());
        Page<Recipe> recipes;

        if(!q.isEmpty()){
             recipes =  recipeDAO.search(pageable, q);
        } else{
            recipes = recipeDAO.findAll(pageable);
        }

        List<RecipeWithIngredientsDTO> recipesList = recipes.getContent()
                .stream()
                .map(item -> {
                    List<CategoryRecipeWithSubcategoriesDTO> categoryRecipes = helperService.transformToListCategoryWithSubcategories(item.getCategories());
                    return new RecipeWithIngredientsDTO(item, categoryRecipes);
                })
                .collect(Collectors.toList());

        return new PageImpl<>(recipesList, pageable, recipes.getTotalElements());
    }


    @Override
    public RecipeWithIngredientsDTO getById(int id) {
        Recipe recipe = recipeDAO.findById(id).orElse(new Recipe());
        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(recipe.getCategories());
        return new RecipeWithIngredientsDTO(recipe, categoryRecipeWithSubcategoriesDTOS);
    }

    @Override
    public List<RecipeWithIngredientsDTO> getByCategories(int id) {
        CategoryRecipe categoryById = categoryRecipeService.getById(id);

        return categoryById.getRecipes()
                .stream()
                .map(RecipeWithIngredientsDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeWithIngredientsDTO create(RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException {
        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(id -> categoryRecipeService.getById(id))
                .collect(Collectors.toList());

        Recipe newRecipe = new Recipe(
                recipe.getTitle(),
                recipe.getDescription(),
                image.getOriginalFilename(),
                categories,
                recipe.getAuthor()
        );
        recipe.getIngredients()
                .forEach(item -> {
                    Ingredient ingredient = ingredientService.findByIdFullInfo(item.getIngredient_id());
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



    @Override
    public RecipeWithIngredientsDTO updateById(int id, RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException {
        if (!recipeDAO.existsById(id)) {
            throw new IllegalArgumentException("No recipe with such id " + id);
        }

        List<CategoryRecipe> categories = recipe.getCategories()
                .stream()
                .map(category_id -> categoryRecipeService.getById(category_id))
                .collect(Collectors.toList());

        Recipe updateRecipe = new Recipe(
                id,
                recipe.getTitle(),
                recipe.getDescription(),
                image.getOriginalFilename(),
                categories,
                recipe.getAuthor()
        );

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = helperService.transformToListCategoryWithSubcategories(categories);

        RecipeWithIngredientsDTO recipeWithIngredientsDTO = new RecipeWithIngredientsDTO(
                recipeDAO.save(updateRecipe),
                categoryRecipeWithSubcategoriesDTOS
        );

        image.transferTo(new File(path + image.getOriginalFilename()));

        return recipeWithIngredientsDTO;
    }

    @Override
    public void deleteById(int id) {
        recipeDAO.deleteById(id);
    }

}
