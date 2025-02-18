package com.example.foodplanningapp.ui.plan.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.PlanAdapter;
import com.example.foodplanningapp.models.Connection;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.ui.plan.presenter.PlanPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PlanFragment extends Fragment implements IPlanView {

    private PlanPresenter planPresenter;
    private PlanAdapter planAdapter;
    RecyclerView recyclerView;
    DatePicker calender;
    String date;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.planrecycle);
        calender=view.findViewById(R.id.calender);
        calender.setMinDate(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calender.setOnDateChangedListener((v, year, monthOfYear, dayOfMonth) -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Calendar selectedCal = Calendar.getInstance();
                selectedCal.set(year, monthOfYear, dayOfMonth);
                date=sdf.format(selectedCal.getTime());
                showMealbyDay(date);

            });
        }



        planPresenter=new PlanPresenter(this,requireContext());
        planAdapter=new PlanAdapter(new ArrayList<>());

        recyclerView.setAdapter(planAdapter);
    }

    @Override
    public void showData(List<MealDTO> list) {
        planAdapter.setList(list);

        planAdapter.setOnItemClickListener(v->{
            if (Connection.isConnected(requireContext()))
            {
                planPresenter.delete(new StoreMeal("plan", Sharedprefrence.getInstance(requireContext()).getUserId(),date,v));
            }
            else
            {
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void deleteMeal(StoreMeal meal) {
        planAdapter.deleteMeal(meal.getMeal());

    }

    public void showMealbyDay(String day)
    {
        planPresenter.showData(Sharedprefrence.getInstance(requireContext()).getUserId(),"plan",day);
    }
}