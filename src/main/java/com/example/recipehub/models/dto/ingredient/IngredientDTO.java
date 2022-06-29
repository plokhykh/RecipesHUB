package com.example.recipehub.models.dto.ingredient;

import com.example.recipehub.models.entity.Ingredient;
import com.example.recipehub.models.entity.IngredientQuantity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private int id;
    private String type_ukr;
    private String name_ukr;
    private String name;
    private String type;
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

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.type_ukr = ingredient.getType_ukr();
        this.name_ukr = ingredient.getName_ukr();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
        this.weight = ingredient.getWeight();
        this.calories = ingredient.getCalories();
        this.total_protein = ingredient.getTotal_protein();
        this.total_fat = ingredient.getTotal_fat();
        this.saturated_fatty_acids = ingredient.getSaturated_fatty_acids();
        this.monounsaturated_fatty_acids = ingredient.getMonounsaturated_fatty_acids();
        this.polyunsaturated_fatty_acids = ingredient.getPolyunsaturated_fatty_acids();
        this.carbohydrate = ingredient.getCarbohydrate();
        this.fiber = ingredient.getFiber();
        this.sugars = ingredient.getSugars();
        this.vitamin_a = ingredient.getVitamin_a();
        this.thiamin_vitamin_b1 = ingredient.getThiamin_vitamin_b1();
        this.riboflavin_vitamin_b2 = ingredient.getRiboflavin_vitamin_b2();
        this.niacin_vitamin_b3 = ingredient.getNiacin_vitamin_b3();
        this.choline_vitamin_b4 = ingredient.getCholine_vitamin_b4();
        this.pantothenic_acid_vitamin_b5 = ingredient.getPantothenic_acid_vitamin_b5();
        this.vitamin_b6 = ingredient.getVitamin_b6();
        this.folic_acid_vitamin_b5 = ingredient.getFolic_acid_vitamin_b5();
        this.vitamin_b12_cobalamin = ingredient.getVitamin_b12_cobalamin();
        this.vitamin_d = ingredient.getVitamin_d();
        this.vitamin_e_alpha_tocopherol = ingredient.getVitamin_e_alpha_tocopherol();
        this.vitamin_k = ingredient.getVitamin_k();
        this.vitamin_c_ascorbic_acid = ingredient.getVitamin_c_ascorbic_acid();
        this.cholesterol = ingredient.getCholesterol();
        this.sodium = ingredient.getSodium();
        this.potassium = ingredient.getPotassium();
        this.calcium = ingredient.getCalcium();
        this.copper = ingredient.getCopper();
        this.phosphorus_p = ingredient.getPhosphorus_p();
        this.magnesium = ingredient.getMagnesium();
        this.iron = ingredient.getIron();
        this.zinc = ingredient.getZinc();
        this.manganese = ingredient.getManganese();
        this.selenium = ingredient.getSelenium();
    }

    public IngredientDTO(Ingredient ingredient, IngredientQuantity ingredientQuantity) {
        this.id = ingredient.getId();
        this.type_ukr = ingredient.getType_ukr();
        this.name_ukr = ingredient.getName_ukr();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
        this.weight = ingredientQuantity.getWeight();
        this.calories = ingredientQuantity.getCalories();
        this.total_protein = ingredientQuantity.getTotal_protein();
        this.total_fat = ingredientQuantity.getTotal_fat();
        this.saturated_fatty_acids = ingredientQuantity.getSaturated_fatty_acids();
        this.monounsaturated_fatty_acids = ingredientQuantity.getMonounsaturated_fatty_acids();
        this.polyunsaturated_fatty_acids = ingredientQuantity.getPolyunsaturated_fatty_acids();
        this.carbohydrate = ingredientQuantity.getCarbohydrate();
        this.fiber = ingredientQuantity.getFiber();
        this.sugars = ingredientQuantity.getSugars();
        this.vitamin_a = ingredientQuantity.getVitamin_a();
        this.thiamin_vitamin_b1 = ingredientQuantity.getThiamin_vitamin_b1();
        this.riboflavin_vitamin_b2 = ingredientQuantity.getRiboflavin_vitamin_b2();
        this.niacin_vitamin_b3 = ingredientQuantity.getNiacin_vitamin_b3();
        this.choline_vitamin_b4 = ingredientQuantity.getCholine_vitamin_b4();
        this.pantothenic_acid_vitamin_b5 = ingredientQuantity.getPantothenic_acid_vitamin_b5();
        this.vitamin_b6 = ingredientQuantity.getVitamin_b6();
        this.folic_acid_vitamin_b5 = ingredientQuantity.getFolic_acid_vitamin_b5();
        this.vitamin_b12_cobalamin = ingredientQuantity.getVitamin_b12_cobalamin();
        this.vitamin_d = ingredientQuantity.getVitamin_d();
        this.vitamin_e_alpha_tocopherol = ingredientQuantity.getVitamin_e_alpha_tocopherol();
        this.vitamin_k = ingredientQuantity.getVitamin_k();
        this.vitamin_c_ascorbic_acid = ingredientQuantity.getVitamin_c_ascorbic_acid();
        this.cholesterol = ingredientQuantity.getCholesterol();
        this.sodium = ingredientQuantity.getSodium();
        this.potassium = ingredientQuantity.getPotassium();
        this.calcium = ingredientQuantity.getCalcium();
        this.copper = ingredientQuantity.getCopper();
        this.phosphorus_p = ingredientQuantity.getPhosphorus_p();
        this.magnesium = ingredientQuantity.getMagnesium();
        this.iron = ingredientQuantity.getIron();
        this.zinc = ingredientQuantity.getZinc();
        this.manganese = ingredientQuantity.getManganese();
        this.selenium = ingredientQuantity.getSelenium();
    }


    public IngredientDTO calculateNutrientsInIngredient(Ingredient ingredient, int grams) {
        this.id = ingredient.getId();
        this.type_ukr = ingredient.getType_ukr();
        this.name_ukr = ingredient.getName_ukr();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
        this.weight = grams;
        this.calories = Double.parseDouble(formula(ingredient.getCalories(), ingredient.getWeight(), grams));
        this.total_protein = Double.parseDouble(formula(ingredient.getTotal_protein(), ingredient.getWeight(), grams));
        this.total_fat = Double.parseDouble(formula(ingredient.getTotal_fat(), ingredient.getWeight(), grams));
        this.saturated_fatty_acids = Double.parseDouble(formula(ingredient.getSaturated_fatty_acids(), ingredient.getWeight(), grams));
        this.monounsaturated_fatty_acids = Double.parseDouble(formula(ingredient.getMonounsaturated_fatty_acids(), ingredient.getWeight(), grams));
        this.polyunsaturated_fatty_acids = Double.parseDouble(formula(ingredient.getPolyunsaturated_fatty_acids(), ingredient.getWeight(), grams));
        this.carbohydrate = Double.parseDouble(formula(ingredient.getCarbohydrate(), ingredient.getWeight(), grams));
        this.fiber = Double.parseDouble(formula(ingredient.getFiber(), ingredient.getWeight(), grams));
        this.sugars = Double.parseDouble(formula(ingredient.getSugars(), ingredient.getWeight(), grams));
        this.vitamin_a = Double.parseDouble(formula(ingredient.getVitamin_a(), ingredient.getWeight(), grams));
        this.thiamin_vitamin_b1 = Double.parseDouble(formula(ingredient.getThiamin_vitamin_b1(),ingredient.getWeight(),grams));
        this.riboflavin_vitamin_b2 = Double.parseDouble(formula(ingredient.getRiboflavin_vitamin_b2(),ingredient.getWeight(),grams));
        this.niacin_vitamin_b3 = Double.parseDouble(formula(ingredient.getNiacin_vitamin_b3(),ingredient.getWeight(), grams));
        this.choline_vitamin_b4 = Double.parseDouble(formula(ingredient.getCholine_vitamin_b4(), ingredient.getWeight(), grams));
        this.pantothenic_acid_vitamin_b5 = Double.parseDouble(formula(ingredient.getPantothenic_acid_vitamin_b5(), ingredient.getWeight(), grams));
        this.vitamin_b6 = Double.parseDouble(formula(ingredient.getVitamin_b6(),ingredient.getWeight(),grams));
        this.folic_acid_vitamin_b5 = Double.parseDouble(formula(ingredient.getFolic_acid_vitamin_b5(),ingredient.getWeight(),grams));
        this.vitamin_b12_cobalamin = Double.parseDouble(formula(ingredient.getVitamin_b12_cobalamin(),ingredient.getWeight(),grams));
        this.vitamin_d = Double.parseDouble(formula(ingredient.getVitamin_d(),ingredient.getWeight(),grams));
        this.vitamin_e_alpha_tocopherol = Double.parseDouble(formula(ingredient.getVitamin_e_alpha_tocopherol(), ingredient.getWeight(), grams));
        this.vitamin_k = Double.parseDouble(formula(ingredient.getVitamin_k(),ingredient.getWeight(), grams));
        this.vitamin_c_ascorbic_acid = Double.parseDouble(formula(ingredient.getVitamin_c_ascorbic_acid(),ingredient.getWeight(), grams));
        this.cholesterol = Double.parseDouble(formula(ingredient.getCholesterol(), ingredient.getWeight(), grams));
        this.sodium = Double.parseDouble(formula(ingredient.getSodium(), ingredient.getWeight(), grams));
        this.potassium = Double.parseDouble(formula(ingredient.getPotassium(), ingredient.getWeight(), grams));
        this.calcium = Double.parseDouble(formula(ingredient.getCalcium(), ingredient.getWeight(), grams));
        this.copper = Double.parseDouble(formula(ingredient.getCopper(), ingredient.getWeight(), grams));
        this.phosphorus_p = Double.parseDouble(formula(ingredient.getPhosphorus_p(), ingredient.getWeight(), grams));
        this.magnesium = Double.parseDouble(formula(ingredient.getMagnesium(), ingredient.getWeight(), grams));
        this.iron = Double.parseDouble(formula(ingredient.getIron(), ingredient.getWeight(), grams));
        this.zinc = Double.parseDouble(formula(ingredient.getZinc(), ingredient.getWeight(), grams));
        this.manganese = Double.parseDouble(formula(ingredient.getManganese(), ingredient.getWeight(), grams));
        this.selenium = Double.parseDouble(formula(ingredient.getSaturated_fatty_acids(), ingredient.getWeight(), grams));
        return this;
    }

    public String formula(double quantityNutrient, int normativeWeight, int calculateWeight) {
        return new DecimalFormat("#0.00").format((quantityNutrient / normativeWeight) * calculateWeight);
    }

}
