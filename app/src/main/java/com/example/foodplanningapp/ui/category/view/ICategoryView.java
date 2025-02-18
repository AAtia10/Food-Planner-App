package com.example.foodplanningapp.ui.category.view;

import com.example.foodplanningapp.models.MealDTO;

import java.util.List;

public interface ICategoryView {
    void showMealsCategory(List<MealDTO>list);
    void navigateToDetails(int id);
}
