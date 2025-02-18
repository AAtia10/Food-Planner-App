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

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

   List<MealDTO> list;
   OnItemClickListener onItemClickListener;

    public PlanAdapter(List<MealDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       MealDTO mealDTO= list.get(position);
        holder.textView.setText(mealDTO.getStrMeal());
        Glide.with(holder.itemView).load(mealDTO.getStrMealThumb()).into(holder.imageView);
        holder.delbtn.setOnClickListener(view -> {
            onItemClickListener.onClicks((mealDTO));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageView delbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView= itemView.findViewById(R.id.imagecard);
            textView= itemView.findViewById(R.id.cardmealtxt);
            delbtn=itemView.findViewById(R.id.deleteimg);

        }

    }

    public void deleteMeal(MealDTO meal) {
        int position = list.indexOf(meal);
        if (position != -1) {
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
        }
    }

    public void setList(List<MealDTO> dtos) {
        this.list.clear();
        this.list.addAll(dtos);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener
    {
        public void onClicks(MealDTO mealDTO);
    }

}
