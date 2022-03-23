package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;

public class DetailActivity extends AppCompatActivity {
    private TextView mealName;
    private TextView mealPrice;
    private TextView mealDesc;
    private ImageView mealImage;
    private Button addToBasketBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        Intent intent = getIntent();

        String meal_name = intent.getStringExtra("MealName");
        String meal_price = intent.getStringExtra("MealPrice");
        String meal_desc = intent.getStringExtra("MealDesc");
        int meal_image = intent.getIntExtra("MealImage",0);

        addToBasketBtn.setOnClickListener(view -> {
            Intent intent1 = new Intent(DetailActivity.this, BasketActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            AppDatabase db = AppDatabase.getDbInstance(this);
            Basket basket = new Basket();
            basket.mealName = meal_name;
            basket.mealPrice = meal_price;
            basket.mealImage = meal_image;
            db.userDao().insertBasket(basket);
            finish();
        });

        mealName.setText(intent.getStringExtra("MealName"));
        mealPrice.setText(intent.getStringExtra("MealPrice"));
        mealDesc.setText(intent.getStringExtra("MealDesc"));
        mealImage.setImageResource(intent.getIntExtra("MealImage",0));

    }


    private void initViews(){
        mealName = findViewById(R.id.mealDetailName);
        mealPrice = findViewById(R.id.mealDetailPrice);
        mealDesc = findViewById(R.id.mealDetails);
        mealImage = findViewById(R.id.mealDetailImg);
        addToBasketBtn = findViewById(R.id.basketBtnDetails);
    }
}