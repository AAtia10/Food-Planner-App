package com.example.foodplanningapp.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanningapp.models.StoreMeal;

import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(StoreMeal meal);

    @Delete
    Completable delete(StoreMeal meal);

    @Query("SELECT * FROM StoreMeal WHERE id=:id AND flag=:flag")
    Single<List<StoreMeal>> getAllFavorites(String id,String flag);

    @Query("SELECT * FROM StoreMeal WHERE id=:id AND flag=:flag AND date=:date")
    Single<List<StoreMeal>> getPlan(String id,String flag, String date);

}
