package com.example.recipehub.services.helper;

import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.categoryRecipe.SubcategoryRecipeDTO;
import com.example.recipehub.models.entity.CategoryRecipe;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class HelperService {

    public CategoryRecipeWithSubcategoriesDTO transformToCategoryWithSubcategories(List<CategoryRecipe> categories, int id) {
        List<SubcategoryRecipeDTO> subCategoryDTO = new ArrayList<>();
        categories.stream()
                .filter(item -> item.getParent() == id)
                .forEach(subcategory -> subCategoryDTO.add(new SubcategoryRecipeDTO(subcategory.getId(), subcategory.getName())));

        CategoryRecipe parentCategory = categories.stream()
                .filter(item -> item.getParent() == 0)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.size() != 1) throw new IllegalStateException();
                            return list.get(0);
                        }));

        return new CategoryRecipeWithSubcategoriesDTO(parentCategory.getId(), parentCategory.getName(), subCategoryDTO);
    }

    public List<CategoryRecipeWithSubcategoriesDTO> transformToListCategoryWithSubcategories(List<CategoryRecipe> categories) {
        List<CategoryRecipe> mainCategory = categories.stream().filter(item -> item.getParent() == 0).collect(Collectors.toList());
        List <CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTOS = new ArrayList<>();
        mainCategory.stream().forEach(category -> {
           List <SubcategoryRecipeDTO> subCategoriesDTO = new ArrayList<>();
            categories.stream().forEach(subcategory -> {
                if(category.getId() == subcategory.getParent()){
                    subCategoriesDTO.add(new SubcategoryRecipeDTO(subcategory.getId(), subcategory.getName()));
                }
            });

            categoryRecipeWithSubcategoriesDTOS.add(new CategoryRecipeWithSubcategoriesDTO(category.getId(), category.getName(), subCategoriesDTO));
        });

        return categoryRecipeWithSubcategoriesDTOS;
    }
}
