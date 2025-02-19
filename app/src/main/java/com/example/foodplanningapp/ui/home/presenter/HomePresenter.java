package com.example.foodplanningapp.ui.home.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanningapp.db.MealLocalDatasource;
import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.RandomMealDTO;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.network.FirebaseLocal;
import com.example.foodplanningapp.network.MealRemoteDatasource;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.home.view.IHomeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private IHomeView homeView;
    private RemoteRepo remoteRepo;
    List<CategoryDTO>catlist=new ArrayList<>();
    List<AreaDTO>arealist=new ArrayList<>();
    List<IngredientsDTO>inglist=new ArrayList<>();
    FirebaseLocal firebaseLocal;
    MealLocalDatasource mealLocalDatasource;

    public HomePresenter(IHomeView homeView, Context context) {
        this.homeView = homeView;
        this.remoteRepo = RemoteRepo.getInstance();
        firebaseLocal=FirebaseLocal.getInstance();
        mealLocalDatasource=MealLocalDatasource.getInstance(context);

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
                        catlist.clear();
                        catlist.addAll(categoryDTOS);

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
                        arealist.clear();
                        arealist.addAll(areaDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public void getIngredients()
    {
        remoteRepo.getIngredients()
                .subscribe(new SingleObserver<List<IngredientsDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<IngredientsDTO> ingredientsDTOS) {
                        homeView.showIngredients(ingredientsDTOS);
                        inglist.clear();
                        inglist.addAll(ingredientsDTOS);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public void searchCategory(String name)
    {
        Observable<CategoryDTO> observable=Observable.fromIterable(catlist);
        observable.filter(item->item.getStrCategory().toLowerCase().contains(name.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CategoryDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<CategoryDTO> categoryDTOS) {
                        homeView.categorySearch(categoryDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }
    public void searchCountry(String name)
    {
        Observable<AreaDTO> observable=Observable.fromIterable(arealist);
        observable.filter(item->item.getStrArea().toLowerCase().contains(name.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<AreaDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<AreaDTO> areaDTOS) {
                        homeView.countrySearch(areaDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public void searchIngredients(String name)
    {
        Observable<IngredientsDTO> observable=Observable.fromIterable(inglist);
        observable.filter(item->item.getStrIngredient().toLowerCase().contains(name.toLowerCase()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<IngredientsDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<IngredientsDTO> ingredientsDTOS) {
                        homeView.IngredientSearch(ingredientsDTOS);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public void getPlan(String id)
    {
        List<StoreMeal>meal=new ArrayList<>();
        firebaseLocal.getPlan("plan",id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren())
                {
                    Log.i("TAG", "onDataChange1: "+snap.getKey());


                        for (DataSnapshot item:snap.getChildren())
                        {
                            Log.i("TAG", "onDataChange: "+item.getKey());
                            meal.add(item.getValue(StoreMeal.class));

                            Log.i("TAG", "onDataChange:3 "+item.getValue(StoreMeal.class).getMeal());

                        }


                    }
                insertMeal(meal);



            }


            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });


    }

    private void insertMeal(List<StoreMeal>meals)
    {
        for (StoreMeal meal:meals)
        {
            mealLocalDatasource.insert(meal)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            ()->{},
                            v->{}
                    );

        }
    }


    public void getFav(String id)
    {

        List<StoreMeal>meal=new ArrayList<>();
        firebaseLocal.getFav("fav",id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren())
                {

                    for (DataSnapshot item:snap.getChildren())
                    {
                        meal.add(item.getValue(StoreMeal.class));

                    }
                }

                insertMeal(meal);
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });


    }


    }



