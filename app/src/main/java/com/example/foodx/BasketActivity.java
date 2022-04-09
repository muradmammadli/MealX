package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodx.Adapters.BasketAdapter;
import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button orderBtn, deleteBtn;
    private BasketAdapter adapter;
    private List<Basket> basketList;
    private AppDatabase db;
    private Toolbar basketToolbar;
    private ImageView addImg;
    private TextView sumPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        initViews();
        basketToolbar.setTitle("Səbət");
        basketToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon_toolbar));
        setSupportActionBar(basketToolbar);
        deleteBtn.setOnClickListener(view -> {
            onDeleteButtonClicked();
        });
        db = AppDatabase.getDbInstance(this.getApplicationContext());
        basketList = db.userDao().getAllBasket();
        initRecyclerView();
        addImg.setOnClickListener(view -> {
            finish();
        });
        sumPrice.setText(sumMealPrice(basketList) + " AZN");

    }

    private int sumMealPrice(List<Basket> basketList) {
        int sum = 0;
        ArrayList<Integer> mealPriceList = new ArrayList<>();
        for (Basket basket : basketList) {
            mealPriceList.add(basket.getMealPrice());
        }
        for (int i : mealPriceList) {
            sum = sum + i;
        }
        return sum;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(this, basketList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        orderBtn = findViewById(R.id.orderBtn);
        deleteBtn = findViewById(R.id.deletBtn);
        basketToolbar = findViewById(R.id.toolbarBasket);
        addImg = findViewById(R.id.addImg);
        sumPrice = findViewById(R.id.sumPrice);
    }

    private void onDeleteButtonClicked() {
        for (Basket basket : basketList) {
            db.userDao().deleteBasket(basket);
        }
        finish();
    }
}