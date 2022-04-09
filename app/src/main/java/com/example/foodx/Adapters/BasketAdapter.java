package com.example.foodx.Adapters;

import static com.example.foodx.DetailActivity.MEAL_COUNT_PREF;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;
import com.example.foodx.R;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketHolder> {
    Context context;
    List<Basket> basketList;

    public BasketAdapter(Context context, List<Basket> basketList) {
        this.context = context;
        this.basketList = basketList;
    }

    @NonNull
    @Override
    public BasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.basket_raw_item,parent,false);
        return new BasketHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BasketHolder holder, int position) {
        Basket basket = basketList.get(position);
        holder.basketMealName.setText(basket.mealName);
        holder.basketMealPrice.setText(basket.getMealPrice() + "AZN");
        holder.basketImg.setImageResource(basket.mealImage);
        holder.deleteBtn.setOnClickListener(view -> {
            AppDatabase db = AppDatabase.getDbInstance(context);
            db.userDao().deleteBasket(basket);
            basketList.remove(basket);
            notifyDataSetChanged();
        });
        Intent intent = new Intent("list-size");
        intent.putExtra("BASKET_LIST_SIZE",basketList.size());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return basketList.size();
    }

    public class BasketHolder extends RecyclerView.ViewHolder {
        TextView basketMealName,basketMealPrice;
        ImageView basketImg,deleteBtn;
        public BasketHolder(@NonNull View itemView) {
            super(itemView);
            basketMealName = itemView.findViewById(R.id.basketMealName);
            basketMealPrice = itemView.findViewById(R.id.basketMealPrice);
            basketImg = itemView.findViewById(R.id.basketImg);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

}
