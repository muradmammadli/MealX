package com.example.foodx.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodx.BasketActivity;
import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;
import com.example.foodx.DetailActivity;
import com.example.foodx.Models.MealModel;
import com.example.foodx.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> implements Filterable {
    Context context;
    List<MealModel> modelList;
    List<MealModel> mealListAll;

    public MealAdapter(Context context,List<MealModel> modelList){
        this.context = context;
        this.modelList = modelList;
        this.mealListAll = new ArrayList<>(modelList);
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
        holder.mealPrice.setText(model.getMeal_price() + "AZN");
        holder.mealImg.setImageResource(model.getMeal_image());
        Log.d("Drawable adapter", String.valueOf(model.getMeal_image()));
        Log.d("Drawable adapter", String.valueOf(model.getMeal_price()));

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("MealName",model.getMeal_name());
            intent.putExtra("MealPrice",model.getMeal_price());
            intent.putExtra("MealImage",model.getMeal_image());
            intent.putExtra("MealDesc",model.getMeal_desc());

            context.startActivity(intent);
        });

        holder.basketBtn.setOnClickListener(view -> {
            AppDatabase db = AppDatabase.getDbInstance(context);
            Basket basket = new Basket();
            basket.mealName = model.getMeal_name();
            basket.mealPrice = model.getMeal_price();
            basket.mealImage = model.getMeal_image();
            basket.mealCount = 1;
            db.userDao().insertBasket(basket);
            Toast.makeText(context, "Sifariş əlavə edildi", Toast.LENGTH_SHORT).show();
            final MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.basket_sound);
            mediaPlayer.start();

        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MealModel> mealList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                mealList.addAll(mealListAll);
            }else {
                for (MealModel meal : mealListAll){
                    if (meal.getMeal_name().contains(charSequence.toString().toLowerCase())){
                         mealList.add(meal);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = mealList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            modelList.clear();
            modelList.addAll((Collection<? extends MealModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        TextView mealPrice;
        ImageView mealImg;
        ImageView mealDescImg;
        CardView cardView;
        Button basketBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealPrice = itemView.findViewById(R.id.mealPrice);
            mealImg = itemView.findViewById(R.id.mealImg);
            mealDescImg = itemView.findViewById(R.id.mealDescImg);
            cardView = itemView.findViewById(R.id.cardView);
            basketBtn = itemView.findViewById(R.id.basketBtnMain);
        }

    }


}
