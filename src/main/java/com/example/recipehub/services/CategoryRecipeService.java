package com.example.recipehub.services;

import com.example.recipehub.dao.CategoryRecipeDAO;
import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.categoryRecipe.SubcategoryRecipeDTO;
import com.example.recipehub.models.entity.CategoryRecipe;
import com.example.recipehub.services.helper.HelperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRecipeService {
    CategoryRecipeDAO categoryRecipeDAO;
    HelperService helperService;

    public CategoryRecipeWithSubcategoriesDTO createCategory(CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
        CategoryRecipe category = new CategoryRecipe(0, categoryRecipe.getName());
        CategoryRecipe savedCategory = categoryRecipeDAO.save(category);

        List<CategoryRecipe> subcategoriesList = categoryRecipe.getSubcategories()
                .stream()
                .map(item -> new CategoryRecipe(savedCategory.getId(), item.getName()))
                .collect(Collectors.toList());
        categoryRecipeDAO.saveAll(subcategoriesList);

        List<SubcategoryRecipeDTO> subCategoriesDTO = subcategoriesList
                .stream()
                .map(item -> new SubcategoryRecipeDTO(item.getId(), item.getName()))
                .collect(Collectors.toList());

        return new CategoryRecipeWithSubcategoriesDTO(savedCategory.getId(), savedCategory.getName(), subCategoriesDTO);
    }


    public List<CategoryRecipeWithSubcategoriesDTO> getAllCategories() {
        List<CategoryRecipe> categories = categoryRecipeDAO.findAll();

        List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeDTOS = new ArrayList<>();

        categories.stream()
                .filter(item -> item.getParent() == 0)
                .forEach(category -> {
                    List<SubcategoryRecipeDTO> subCategoryDTOS = categories
                            .stream()
                            .filter(subcategory -> category.getId() == subcategory.getParent())
                            .map(item -> new SubcategoryRecipeDTO(item.getId(), item.getName()))
                            .collect(Collectors.toList());
                    categoryRecipeDTOS.add(new CategoryRecipeWithSubcategoriesDTO(category.getId(), category.getName(), subCategoryDTOS));
                });

        return categoryRecipeDTOS;
    }


    public ResponseEntity<CategoryRecipeWithSubcategoriesDTO> getCategoryWithSubcategoriesById(int id) {
        List<CategoryRecipe> categories = categoryRecipeDAO.findAllByParentOrId(id, id);

        return new ResponseEntity<>(helperService.transformToCategoryWithSubcategories(categories, id), HttpStatus.OK);
    }


    public CategoryRecipe getCategoryById(int id) {
        return categoryRecipeDAO.findById(id).orElse(new CategoryRecipe());
    }


    public ResponseEntity<CategoryRecipeWithSubcategoriesDTO> updateCategoryWithSubcategory(int id, CategoryRecipeWithSubcategoriesDTO categoryRecipeDTO) {
        List<CategoryRecipe> categories = categoryRecipeDTO.getSubcategories()
                .stream()
                .map(item -> new CategoryRecipe(item.getId(), id, item.getName()))
                .collect(Collectors.toList());

        categories.add(new CategoryRecipe(id, 0, categoryRecipeDTO.getName()));

        categoryRecipeDAO.saveAll(categories);
        List<CategoryRecipe> allSavedCategories = categoryRecipeDAO.findAllByParentOrId(id, id);
        return new ResponseEntity<>(helperService.transformToCategoryWithSubcategories(allSavedCategories, id), HttpStatus.OK);
    }


    public void deleteCategory(int id) {
        categoryRecipeDAO.deleteById(id);
    }

    public CategoryRecipe findCategoryRecipe(int id) {
        return categoryRecipeDAO.findById(id).orElse(new CategoryRecipe());
    }

}
