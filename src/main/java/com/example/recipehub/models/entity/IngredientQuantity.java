package com.example.recipehub.models.entity;

import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor

@Getter
@Setter
public class IngredientQuantity {
    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipe_id")
    private Recipe recipe;

    @Column
    private int weight;
    private double calories;
    private double total_protein;
    private double total_fat;
    private double saturated_fatty_acids;
    private double monounsaturated_fatty_acids;
    private double polyunsaturated_fatty_acids;
    private double carbohydrate;
    private double fiber;
    private double sugars;
    private double vitamin_a;
    private double thiamin_vitamin_b1;
    private double riboflavin_vitamin_b2;
    private double niacin_vitamin_b3;
    private double choline_vitamin_b4;
    private double pantothenic_acid_vitamin_b5;
    private double vitamin_b6;
    private double folic_acid_vitamin_b5;
    private double vitamin_b12_cobalamin;
    private double vitamin_d;
    private double vitamin_e_alpha_tocopherol;
    private double vitamin_k;
    private double vitamin_c_ascorbic_acid;
    private double cholesterol;
    private double sodium;
    private double potassium;
    private double calcium;
    private double copper;
    private double phosphorus_p;
    private double magnesium;
    private double iron;
    private double zinc;
    private double manganese;
    private double selenium;

    public IngredientQuantity(Recipe recipe, Ingredient ingredient, IngredientDTO calculatedIngredient) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
        this.weight = calculatedIngredient.getWeight();
        this.calories = calculatedIngredient.getCalories();
        this.total_protein = calculatedIngredient.getTotal_protein();
        this.total_fat = calculatedIngredient.getTotal_fat();
        this.saturated_fatty_acids = calculatedIngredient.getSaturated_fatty_acids();
        this.monounsaturated_fatty_acids = calculatedIngredient.getMonounsaturated_fatty_acids();
        this.polyunsaturated_fatty_acids = calculatedIngredient.getPolyunsaturated_fatty_acids();
        this.carbohydrate = calculatedIngredient.getCarbohydrate();
        this.fiber = calculatedIngredient.getFiber();
        this.sugars = calculatedIngredient.getSugars();
        this.vitamin_a = calculatedIngredient.getVitamin_a();
        this.thiamin_vitamin_b1 = calculatedIngredient.getThiamin_vitamin_b1();
        this.riboflavin_vitamin_b2 = calculatedIngredient.getRiboflavin_vitamin_b2();
        this.niacin_vitamin_b3= calculatedIngredient.getNiacin_vitamin_b3();
        this.choline_vitamin_b4 = calculatedIngredient.getCholine_vitamin_b4();
        this.pantothenic_acid_vitamin_b5 = calculatedIngredient.getPantothenic_acid_vitamin_b5();
        this.vitamin_b6 = calculatedIngredient.getVitamin_b6();
        this.folic_acid_vitamin_b5 = calculatedIngredient.getFolic_acid_vitamin_b5();
        this.vitamin_b12_cobalamin = calculatedIngredient.getVitamin_b12_cobalamin();
        this.vitamin_d = calculatedIngredient.getVitamin_d();
        this.vitamin_e_alpha_tocopherol = calculatedIngredient.getVitamin_e_alpha_tocopherol();
        this.vitamin_k= calculatedIngredient.getVitamin_k();
        this.vitamin_c_ascorbic_acid = calculatedIngredient.getVitamin_c_ascorbic_acid();
        this.cholesterol= calculatedIngredient.getCholesterol();
        this.sodium= calculatedIngredient.getSodium();
        this.potassium = calculatedIngredient.getPotassium();
        this.calcium = calculatedIngredient.getCalcium();
        this.copper = calculatedIngredient.getCopper();
        this.phosphorus_p = calculatedIngredient.getPhosphorus_p();
        this.magnesium = calculatedIngredient.getMagnesium();
        this.iron = calculatedIngredient.getIron();
        this.zinc = calculatedIngredient.getZinc();
        this.manganese = calculatedIngredient.getManganese();
        this.selenium = calculatedIngredient.getSelenium();
    }
}
