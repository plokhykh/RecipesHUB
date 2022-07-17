package com.example.recipehub.services.categoryRecipe;

import com.example.recipehub.dao.CategoryRecipeDAO;
import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.categoryRecipe.SubcategoryRecipeDTO;
import com.example.recipehub.models.entity.CategoryRecipe;
import com.example.recipehub.services.helper.HelperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRecipeService implements ICategoryRecipeService {

    CategoryRecipeDAO categoryRecipeDAO;
    HelperService helperService;

    @Override
    public List<CategoryRecipeWithSubcategoriesDTO> getAll() {
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

    @Override
    public CategoryRecipe getById(int id) {
        return categoryRecipeDAO.findById(id).orElse(new CategoryRecipe());
    }

    @Override
    public CategoryRecipeWithSubcategoriesDTO getByIdWithSubcategories(int id) {
        List<CategoryRecipe> categories = categoryRecipeDAO.findAllByParentOrId(id, id);
        return helperService.transformToCategoryWithSubcategories(categories, id);
    }

    @Override
    public CategoryRecipeWithSubcategoriesDTO create(CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
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

    @Override
    public CategoryRecipeWithSubcategoriesDTO updateWithSubcategory(int id, CategoryRecipeWithSubcategoriesDTO categoryRecipeDTO) {
        List<CategoryRecipe> categories = categoryRecipeDTO.getSubcategories()
                .stream()
                .map(item -> new CategoryRecipe(item.getId(), id, item.getName()))
                .collect(Collectors.toList());

        categories.add(new CategoryRecipe(id, 0, categoryRecipeDTO.getName()));

        categoryRecipeDAO.saveAll(categories);
        List<CategoryRecipe> allSavedCategories = categoryRecipeDAO.findAllByParentOrId(id, id);

        return helperService.transformToCategoryWithSubcategories(allSavedCategories, id);
    }

    @Override
    public void deleteById(int id) {
        categoryRecipeDAO.deleteById(id);
    }
}
