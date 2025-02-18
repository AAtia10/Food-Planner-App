package com.example.foodplanningapp.ui.ingredient.view;

import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;

import java.util.List;

public interface IIngredientView {

    void showMealsIngredients(List<MealDTO> list);
    void navigateToDetails(int id);
}
