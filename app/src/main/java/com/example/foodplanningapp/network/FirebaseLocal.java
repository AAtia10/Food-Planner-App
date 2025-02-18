package com.example.foodplanningapp.network;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.models.StoreMeal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseLocal {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    private static FirebaseLocal instance;

    private FirebaseLocal() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("meal");
    }

    public static FirebaseLocal getInstance()
    {
        if (instance==null)
        {
            instance= new FirebaseLocal();
        }
        return instance;
    }

    public void Insert(StoreMeal local)
    {
        myref.child(local.getFlag())
                .child(local.getId())
                .child(local.getDate())
                .child(local.getMeal().getIdMeal())
                .setValue(local);
    }

    public void deleteFirebase(String id, MealDTO meal,String date,String flag)
    {
        myref.child(flag)
                .child(id)
                .child(date)
                .child(meal.getIdMeal())
                .removeValue();
    }
    public DatabaseReference getPlan(String flag,String id)
    {
        return myref.child(flag)
                .child(id);

    }

    public DatabaseReference getFav(String flag,String id)
    {
        return myref.child(flag)
                .child(id);
    }
}
