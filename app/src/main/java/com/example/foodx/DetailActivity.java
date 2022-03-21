package com.example.foodx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView mealName;
    private TextView mealPrice;
    private TextView mealDesc;
    private ImageView mealImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        Intent intent = getIntent();

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
    }
}