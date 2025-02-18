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
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.IngredientsDTO;

import java.util.List;

public class IngridentAdapter extends RecyclerView.Adapter<IngridentAdapter.ViewHolder> {

   List<IngredientsDTO> list;
   OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(List<IngredientsDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public IngridentAdapter(List<IngredientsDTO> list) {
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
        IngredientsDTO ingredientsDTO= list.get(position);
        holder.txtviewm.setVisibility(View.GONE);
        holder.textView.setText(ingredientsDTO.getStrIngredient());
        String url="https://www.themealdb.com/images/ingredients/"+ingredientsDTO.getStrIngredient()+".png";
        Glide.with(holder.itemView).load(url).into(holder.imageView);
        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onClicks(ingredientsDTO.getStrIngredient());
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView txtviewm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findById(itemView);
        }

        private void findById(@NonNull View itemView) {
            imageView= itemView.findViewById(R.id.ingrimg);
            textView= itemView.findViewById(R.id.ingrtittle);
            txtviewm= itemView.findViewById(R.id.ingramount);

        }

    }

    public interface OnItemClickListener
    {
        public void onClicks(String s);
    }

}
