package com.example.foodplanningapp.ui.home.presenter;

import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.RandomMealDTO;
import com.example.foodplanningapp.network.MealRemoteDatasource;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.home.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenter {
    private IHomeView homeView;
    private RemoteRepo remoteRepo;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
        this.remoteRepo = RemoteRepo.getInstance();

    }

    public void getDailyMeals() {
        remoteRepo.getDailyMeals()
                .subscribe(new SingleObserver<List<MealDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<MealDTO> mealDTOS) {
                        homeView.showDailyMeals(mealDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


    }

    public void getAllCategories()
    {
        remoteRepo.getAllCategories()
                .subscribe(new SingleObserver<List<CategoryDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<CategoryDTO> categoryDTOS) {
                        homeView.showAllCategories(categoryDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public void getArea()
    {
        remoteRepo.getArea()
                .subscribe(new SingleObserver<List<AreaDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<AreaDTO> areaDTOS) {
                        homeView.showAreas(areaDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


}
