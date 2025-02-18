package com.example.foodplanningapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverters;

import com.example.foodplanningapp.models.StoreMeal;


@Database(entities = {StoreMeal.class}, version = 1)
@TypeConverters({ConvertObject.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract MealDao getDao();
}
