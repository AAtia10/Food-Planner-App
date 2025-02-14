package com.example.foodplanningapp.network;

import android.util.Log;

import com.example.foodplanningapp.models.AreaResponse;
import com.example.foodplanningapp.models.CategoryResponse;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.RandomMealDTO;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    public Single<RandomMealDTO> getDailyMeals(){
        return service.getMeals();

    }

    public Single<CategoryResponse> getAllCategories()
    {
        return service.getAllCategories();
    }

    public Single<RandomMealDTO> getDetails(int id){
        return service.getDetails(id);

    }
    public Single<AreaResponse>getArea()
    {
        return service.getAreas();
    }


}
