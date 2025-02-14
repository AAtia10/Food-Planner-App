package com.example.foodplanningapp.ui.home.presenter;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.home.view.IDetailView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailPresenter {
    IDetailView detailView;
    RemoteRepo remoteRepo;

    public DetailPresenter(IDetailView detailView) {
        this.detailView = detailView;
        this.remoteRepo = RemoteRepo.getInstance();
    }
    public void getDetails(int id)
    {
        remoteRepo.getDetails(id)
                .subscribe(new SingleObserver<MealDTO>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealDTO mealDTO) {
                        detailView.showData(mealDTO);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
