package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodx.Database.AppDatabase;
import com.example.foodx.Database.Basket;

public class DetailActivity extends AppCompatActivity {
    private TextView mealName;
    private TextView mealPrice;
    private TextView mealDesc;
    private TextView mealCount;
    private ImageView mealImage, plusImg, minusImg;
    private Button addToBasketBtn;
    private int meal_count;
    public static final String MEAL_COUNT_PREF = "MealCount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        meal_count = 0;

        Intent intent = getIntent();
        String meal_name = intent.getStringExtra("MealName");
        int meal_price = intent.getIntExtra("MealPrice", 0);
        String meal_desc = intent.getStringExtra("MealDesc");
        int meal_image = intent.getIntExtra("MealImage", 0);

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
        mealName.setText(meal_name);
        mealPrice.setText(meal_price + "AZN");
        mealDesc.setText(meal_desc);
        mealImage.setImageResource(meal_image);

        plusImg.setOnClickListener(view -> {
            try {
                String presentValStr = mealCount.getText().toString();
                int presentValInt = Integer.parseInt(presentValStr);
                presentValInt++;
                mealCount.setText(String.valueOf(presentValInt));
                int mealSumPriceInt = presentValInt * meal_price;
                mealPrice.setText(mealSumPriceInt + "AZN");
//                SharedPreferences.Editor editor = getSharedPreferences(MEAL_COUNT_PREF,MODE_PRIVATE).edit();
//                editor.putInt("meal_count",presentValInt);
//                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        minusImg.setOnClickListener(view -> {
            try {
                String presentValStr = mealCount.getText().toString();
                int presentValInt = Integer.parseInt(presentValStr);
                if (presentValInt > 1) {
                    presentValInt--;
                    mealCount.setText(String.valueOf(presentValInt));
                    int mealSumPriceInt = presentValInt * meal_price;
                    mealPrice.setText(mealSumPriceInt + "AZN");
                    SharedPreferences.Editor editor = getSharedPreferences(MEAL_COUNT_PREF,MODE_PRIVATE).edit();
                    editor.putInt("meal_count",mealSumPriceInt);
                    editor.apply();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void initViews() {
        mealName = findViewById(R.id.mealDetailName);
        mealPrice = findViewById(R.id.mealDetailPrice);
        mealDesc = findViewById(R.id.mealDetails);
        mealImage = findViewById(R.id.mealDetailImg);
        mealCount = findViewById(R.id.mealDetailCount);
        addToBasketBtn = findViewById(R.id.basketBtnDetails);
        plusImg = findViewById(R.id.plusImg);
        minusImg = findViewById(R.id.minusImg);
    }
}