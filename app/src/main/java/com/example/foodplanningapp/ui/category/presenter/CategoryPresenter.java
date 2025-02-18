package com.example.foodplanningapp.ui.category.presenter;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.category.view.ICategoryView;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class CategoryPresenter {
    RemoteRepo remoteRepo;
    ICategoryView iCategoryView;

    public CategoryPresenter( ICategoryView iCategoryView) {
        this.iCategoryView = iCategoryView;
        remoteRepo=RemoteRepo.getInstance();
    }

    public void showCategory(String name)
    {
        remoteRepo.getMealCategory(name).subscribe(new SingleObserver<List<MealDTO>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<MealDTO> list) {
                iCategoryView.showMealsCategory(list);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
