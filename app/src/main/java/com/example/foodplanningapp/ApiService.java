package com.example.foodplanningapp;


import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("meals")
    Call<RandomMealDTO> getMeals();

    }

