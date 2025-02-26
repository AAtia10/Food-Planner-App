package com.example.foodplanningapp.ui.home.view;

import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;

import java.util.List;

public interface IHomeView {
    void showDailyMeals(List<MealDTO>list);
    void showError(String msg);
    void showAllCategories(List<CategoryDTO>list);
    void showAreas(List<AreaDTO>list);
    void showIngredients(List<IngredientsDTO>list);
    void categorySearch(List<CategoryDTO>list);
    void countrySearch(List<AreaDTO>list);
    void IngredientSearch(List<IngredientsDTO>list);

}
