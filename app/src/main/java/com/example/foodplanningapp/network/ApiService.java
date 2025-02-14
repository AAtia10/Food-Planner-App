package com.example.foodplanningapp.network;


import com.example.foodplanningapp.models.AreaResponse;
import com.example.foodplanningapp.models.CategoryResponse;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.RandomMealDTO;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    @GET("random.php")
    Single<RandomMealDTO> getMeals();
    @GET("categories.php")
    Single<CategoryResponse> getAllCategories();
    @GET("lookup.php")
    Single<RandomMealDTO>getDetails(@Query("i")int id);
    @GET("list.php?a=list")
    Single<AreaResponse>getAreas();

    }

