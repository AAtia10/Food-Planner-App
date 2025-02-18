package com.example.foodplanningapp.ui.country.view.presenter;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.country.view.view.ICountryView;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class CountryPresenter {
    RemoteRepo remoteRepo;
    ICountryView iCountryView;

    public CountryPresenter( ICountryView iCountryView) {
        remoteRepo = RemoteRepo.getInstance();
        this.iCountryView = iCountryView;
    }

    public void showCountry(String name)
    {
        remoteRepo.getMealCountry(name).subscribe(new SingleObserver<List<MealDTO>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<MealDTO> list) {
                iCountryView.showMealsCountry(list);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
