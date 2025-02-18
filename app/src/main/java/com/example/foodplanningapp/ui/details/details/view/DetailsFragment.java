package com.example.foodplanningapp.ui.details.details.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.MeasureAdapter;
import com.example.foodplanningapp.databinding.FragmentDetailsBinding;
import com.example.foodplanningapp.models.FlagHelper;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.ui.details.details.presenter.DetailPresenter;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DetailsFragment extends Fragment implements IDetailView {
    private DetailPresenter detailPresenter;
    private FragmentDetailsBinding binding;
    private MeasureAdapter measureAdapter;
    private MealDTO mealDTO;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentDetailsBinding.bind(view);
        int id=DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        detailPresenter=new DetailPresenter(this,requireContext());
        detailPresenter.getDetails(id);


    }

    @Override
    public void showData(MealDTO mealDTO) {
        this.mealDTO=mealDTO;
       binding.mealtittle.setText(mealDTO.getStrMeal());
       binding.cattittle.setText(mealDTO.getStrCategory());
       binding.countryname.setText(mealDTO.getStrArea());
       binding.steps.setText(mealDTO.getStrInstructions());
       measureAdapter=new MeasureAdapter(mealDTO.getMeasureList());
       binding.ingrrecycle.setAdapter(measureAdapter);
       binding.favouriteicon.setOnClickListener(view -> {
           if (Sharedprefrence.getInstance(requireContext()).getUserId()!=null)
           {
               detailPresenter.insert(new StoreMeal("fav", Sharedprefrence.getInstance(requireContext()).getUserId(),"date",mealDTO));
               Snackbar.make(requireView(), "Added to Favourite Successfully", Snackbar.LENGTH_SHORT).show();
           }
           else
           {
               Snackbar.make(requireView(), "Sign up First to gain all features", Snackbar.LENGTH_SHORT).show();
           }


       });

       binding.planimage.setOnClickListener(view -> {
           if (Sharedprefrence.getInstance(requireContext()).getUserId()!=null)
           {
               showCalender(requireContext(),date->{

                   detailPresenter.insert(new StoreMeal("plan",Sharedprefrence.getInstance(requireContext()).getUserId(),date,mealDTO));
                   Snackbar.make(requireView(), "Added to Plan Successfully", Snackbar.LENGTH_SHORT).show();
               });

           }
           else
           {
               Snackbar.make(requireView(), "Sign up First to gain all features", Snackbar.LENGTH_SHORT).show();

           }


       });






        Glide.with(getContext()).load(mealDTO.getStrMealThumb()).into(binding.mealimg);
        getVideo(mealDTO.getStrYoutube());

        String url= FlagHelper.getUrl(mealDTO.getStrArea());
        Glide.with(getContext()).load(url).into(binding.flagitem);


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }

    private void getVideo(String youtubeUrl) {
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        binding.webview.setWebChromeClient(new WebChromeClient());

        Uri uri = Uri.parse(youtubeUrl);
        String videoId = uri.getQueryParameter("v");

        if (videoId != null) {
            String iframe = "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" + videoId + "?autoplay=0&mute=0\" " +
            "frameborder=\"0\" allowfullscreen></iframe>";

            binding.webview.loadData(iframe, "text/html", "utf-8");
        }
    }

    private void showCalender(Context context,DateCallback callback)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                    Calendar selectedCal = Calendar.getInstance();
                    selectedCal.set(selectedYear, selectedMonth, selectedDay);
                    String selectedDate = sdf.format(selectedCal.getTime());
                    callback.SelectedDate(selectedDate);

                },


                year, month, day
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }


}