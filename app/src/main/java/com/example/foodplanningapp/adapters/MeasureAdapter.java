package com.example.foodplanningapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.MeasureDTO;

import java.util.List;

public class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.ViewHolder> {

   List<MeasureDTO> list;


    public MeasureAdapter(List<MeasureDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingrident_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeasureDTO measureDTO= list.get(position);
        holder.title.setText(measureDTO.getTitle());
        holder.measure.setText(measureDTO.getMeasure());
        String url="https://www.themealdb.com/images/ingredients/"+measureDTO.getTitle()+".png";
        Glide.with(holder.itemView).load(url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView measure;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView= itemView.findViewById(R.id.ingrimg);
            title= itemView.findViewById(R.id.ingrtittle);
            measure=itemView.findViewById(R.id.ingramount);

        }

    }


}
