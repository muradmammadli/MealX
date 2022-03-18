package com.example.foodx;

import android.content.Context;
import android.hardware.lights.LightState;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    Context context;
    List<MealModel> modelList;

    public MealAdapter(Context context,List<MealModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.meal_card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealModel model = modelList.get(position);
        holder.mealName.setText(model.getMeal_name());
        holder.mealPrice.setText(model.getMeal_price());
        holder.mealImg.setImageResource(model.getMeal_image());
        Log.d("Drawable adapter", String.valueOf(model.getMeal_image()));
        Log.d("Drawable adapter", String.valueOf(model.getMeal_price()));

        holder.cardView.setOnClickListener(view -> {
            Toast.makeText(context, "Clicked card", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        TextView mealPrice;
        ImageView mealImg;
        ImageView mealDescImg;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealPrice = itemView.findViewById(R.id.mealPrice);
            mealImg = itemView.findViewById(R.id.mealImg);
            mealDescImg = itemView.findViewById(R.id.mealDescImg);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }




}
