package com.example.recipehub.services.recipe;


import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IRecipeService {

    PageImpl<RecipeWithIngredientsDTO> getAll(DefaultPage recipePage, String q);

    RecipeWithIngredientsDTO getById(int id);

    List<RecipeWithIngredientsDTO> getByCategories(int id);

    RecipeWithIngredientsDTO create(RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException;

    RecipeWithIngredientsDTO updateById(int id, RecipeWithIngredientsIdDTO recipe, MultipartFile image) throws IOException;

    void deleteById (int id);


}
