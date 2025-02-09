package com.example.foodplanningapp;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDatasource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    ApiService service;

    private static MealRemoteDatasource instance=null;
    private MealRemoteDatasource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(ApiService.class);

    }

    public static MealRemoteDatasource getInstance() {
        if(instance==null)
        {
            instance=new MealRemoteDatasource();
        }
        return instance;
    }

    public void getDataOverNetwork(NetworkCallback callback){
        service.getMeals().enqueue(new Callback<MealDTO>() {

            @Override
            public void onResponse(Call<MealDTO> call, Response<MealDTO> response) {
                callback.onSuccess(response.body().);

            }

            @Override
            public void onFailure(Call<MealDTO> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface NetworkCallback{

        void onSuccess(List<RandomMealDTO> products);

        void onFailure(String message);
    }
}
