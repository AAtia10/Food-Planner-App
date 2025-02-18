package com.example.foodplanningapp.ui.favourite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.FavouriteAdapter;
import com.example.foodplanningapp.models.Connection;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.ui.favourite.presenter.FavouritePresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class FavouriteFragment extends Fragment implements IFavouriteView{
    private RecyclerView recyclerView;
   private FavouriteAdapter dailyMealAdapter;
    private FavouritePresenter favouritePresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.favouriterecycle);
        favouritePresenter=new FavouritePresenter(this,requireContext());
        favouritePresenter.showData(Sharedprefrence.getInstance(requireContext()).getUserId(),"fav");
    }

    @Override
    public void showData(List<MealDTO> list) {
        dailyMealAdapter=new FavouriteAdapter(list);
        recyclerView.setAdapter(dailyMealAdapter);
        dailyMealAdapter.setOnItemClickListener(v->{
            Log.i("TAG", "showData:fsfsdfsdf "+Sharedprefrence.getInstance(requireContext()).getUserId());

            if(Connection.isConnected(requireContext()))
            {
                favouritePresenter.delete(new StoreMeal("fav",Sharedprefrence.getInstance(requireContext()).getUserId(),"27-11",v));
                dailyMealAdapter.deleteMeal(v);
            }
            else
            {
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();

            }

        });

    }


}